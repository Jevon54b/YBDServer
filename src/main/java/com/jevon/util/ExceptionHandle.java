package com.jevon.util;

import com.jevon.entity.ServerResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    /**
     * 全局异常处理
     * @author jevon
     * @since  2021
     * @param  e 被捕获的异常
     * @return  void
     */
    @ExceptionHandler(value = Exception.class)
    public ServerResponse handle(Exception e){
        e.printStackTrace();
        logger.error(e.getMessage());
        return ServerResponse.createByErrorCodeMessage(500,e.getMessage());
    }

}
