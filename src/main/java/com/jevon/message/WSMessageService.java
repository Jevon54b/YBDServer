package com.jevon.message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("webSocketMessageService")
public class WSMessageService {
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    //声明websocket连接类
    private WSManager websocket = new WSManager();

    /**
     * @Title: sendToAllTerminal
     * @Description: 调用websocket类给用户下的所有终端发送消息
     * @param @param userId 用户id
     * @param @param message 消息
     * @param @return 发送成功返回true，否则返回false
     */
    public Boolean sendMessage(Long supportId,Message message){
        if(websocket.sendMessageToAll(supportId,message.toJsonString())){
            return true;
        }else{
            return false;
        }
    }

    public SupportInfo getSupportInfo(){
        String id = websocket.getFreeSupportId();
        if (id.equals("0")) return new SupportInfo(0,"");
        else if(id.equals("-1")) return new SupportInfo(-1,"");
        else return new SupportInfo(1,id);
    }

    public void closeAllSupport(){
        websocket.closeAllSupport();
    }
}
