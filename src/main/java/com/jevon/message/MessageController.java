package com.jevon.message;

import com.jevon.entity.ServerResponse;
import com.jevon.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/message/")
@Controller
public class MessageController {
    @Autowired
    private WSMessageService wsMessageService;

    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    //请求入口
    @RequestMapping(value="sendMessage.do",method= RequestMethod.GET)
    @ResponseBody
    public ServerResponse sendMessage(@RequestParam(value="supportId") Long supportId,
                                 @RequestParam(value="name") String name,
                                 @RequestParam(value="message") String message,
                                 @RequestParam(value = "type") int type){


        logger.warn("收到发送请求，向用户{}的消息：{}",supportId,message);
        if(wsMessageService.sendMessage(supportId, new Message(name,message,type, StringUtils.getCurrentTimeStr()))){
            return ServerResponse.createBySuccess();
        }else{
            return ServerResponse.createByError();
        }
    }


    @RequestMapping(value = "getSupportInfo.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getSupportInfo(){
        logger.warn("用户发起获取支持请求");
        SupportInfo supportInfo = wsMessageService.getSupportInfo();
        if (supportInfo.getCode() == 0){
            return ServerResponse.createBySuccess("当前无客服在线",supportInfo);
        }else if (supportInfo.getCode() < 0 ){
            return ServerResponse.createBySuccess("客服忙线，请稍后再来。",supportInfo);
        }else {
            return ServerResponse.createBySuccess("客服空闲",supportInfo);
        }
    }


}
