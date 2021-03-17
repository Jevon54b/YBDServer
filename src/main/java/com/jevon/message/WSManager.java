package com.jevon.message;

import com.jevon.util.StringUtils;
import org.apache.ibatis.javassist.bytecode.Descriptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint(value = "/WebSocketTest2/{supportId}/{type}",configurator = SpringConfigurator.class)
public class WSManager {
    private static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //记录每个用户下多个终端的连接
    private static Map<Long, Set<WSManager>> supportSockets = new HashMap<>();

    //需要session来对用户发送数据, 获取连接特征supportId
    private Session session;
    private Long supportId;
    private int type; //0 -> 客服  1-> 用户
    private static final int SUPPORT_TYPE = 0;
    private static final int USER_TYPE = 1;

    /**
     * @Title: onOpen
     * @Description: websocekt连接建立时的操作
     * @param @param supportId 用户id
     * @param @param session websocket连接的session属性
     * @param @throws IOException
     */
    @OnOpen
    public void onOpen(@PathParam("supportId") Long supportId,@PathParam("type")int type,Session session) throws IOException {
        this.session = session;
        this.supportId = supportId;
        this.type = type;
        onlineCount++;
        //根据该用户当前是否已经在别的终端登录进行添加操作
        if (supportSockets.containsKey(this.supportId)) {
            logger.debug("当前用户id:{}已有其他终端登录",this.supportId);
            supportSockets.get(this.supportId).add(this); //增加该用户set中的连接实例
        }else {
            logger.debug("当前用户id:{}第一个终端登录",this.supportId);
            Set<WSManager> addUserSet = new HashSet<>();
            addUserSet.add(this);
            supportSockets.put(this.supportId, addUserSet);
        }
        logger.debug("用户{}登录的终端个数是为{}",supportId,supportSockets.get(this.supportId).size());
        logger.debug("当前在线用户数为：{},所有终端个数为：{}",supportSockets.size(),onlineCount);
    }

    /**
     * @Title: onClose
     * @Description: 连接关闭的操作
     */
    @OnClose
    public void onClose(){
        //移除当前用户终端登录的websocket信息,如果该用户的所有终端都下线了，则删除该用户的记录
        if (supportSockets.get(this.supportId).size() == 0) {
            supportSockets.remove(this.supportId);
        }else{
            //客服处断开连接 客户处同样需要断开
            if (this.type == SUPPORT_TYPE){
                supportSockets.get(this.supportId).remove(this);
                Iterator<WSManager> it = supportSockets.get(this.supportId).iterator();
                while (it.hasNext()){
                    WSManager e = it.next();
                    if (e.session!=null){
                        try {
                            e.session.close(); //断开另一端前通知对方？
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
                supportSockets.remove(this.supportId);
            }else{
                //客户处断开仅需移除客户session,无需移除support连接,并通知客服。
                supportSockets.get(this.supportId).remove(this);
                Iterator<WSManager> it = supportSockets.get(this.supportId).iterator();
                while (it.hasNext()){
                    WSManager e = it.next();
                    if (e.session!=null){
                        try {
                            e.session.getBasicRemote().sendText(new Message("Server","用户已断开连接",-1, StringUtils.getCurrentTimeStr()).toJsonString());
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            }

        }
    }

    /**
     * @Title: onMessage
     * @Description: 收到消息后的操作
     * @param @param message 收到的消息
     * @param @param session 该连接的session属性
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        if(session ==null)  logger.debug("session null");
        logger.error("Received message :"+message +",sessionId = "+session.getId());
        logger.error("Now session Id = "+this.session.getId());
        if (supportSockets.containsKey(supportId)) {
            for (WSManager ws : supportSockets.get(supportId)) {
                if (ws.session.getId() != session.getId()){
                    try {
                        logger.error("Send message from "+session.getId() +" to "+ws.session.getId());
                        ws.session.getBasicRemote().sendText(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    /**
     * @Title: onError
     * @Description: 连接发生错误时候的操作
     * @param @param session 该连接的session
     * @param @param error 发生的错误
     */
    @OnError
    public void onError(Session session, Throwable error){
        logger.debug("用户id为：{}的连接发送错误",this.supportId);
        error.printStackTrace();
    }

    /**
     * @Title: sendMessageToAll
     * @Description: 发送消息给服务下的所有终端
     * @param @param supportId 用户id
     * @param @param message 发送的消息
     * @param @return 发送成功返回true，反则返回false
     */
    public Boolean sendMessageToAll(Long supportId,String message){
        if (supportSockets.containsKey(supportId)) {
            logger.debug(" 给用户id为：{}的所有终端发送消息：{}",supportId,message);
            for (WSManager WS : supportSockets.get(supportId)) {
                logger.debug("sessionId为:{}",WS.session.getId());
                try {
                    logger.error("发送消息:"+message);
                    WS.session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    logger.error("发送消息失败");
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }
        logger.debug("发送错误：当前连接不包含id为：{}的用户",supportId);
        return false;
    }

    public Long getFreeSupportId(){
        if (supportSockets.size()<=0){
            return 0L;
        }else{
            Iterator<Map.Entry<Long,Set<WSManager>>> it = supportSockets.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<Long,Set<WSManager>> e = it.next();
                if (e.getValue().size()>1) continue;
                else return e.getKey();
            }
            return -1L;
        }
    }

}
