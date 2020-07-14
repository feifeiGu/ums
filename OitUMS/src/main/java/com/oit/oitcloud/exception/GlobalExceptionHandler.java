package com.oit.oitcloud.exception;

import com.oit.oitcloud.entity.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    RestResponse handleException(Exception e){
//        LOGGER.error(e.getMessage(), e);
//        return RestResponse.fail("操作失败！");
//    }

    /**
     * 处理所有业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    RestResponse handleBusinessException(BusinessException e){
        LOGGER.error(e.getMessage(), e);
        return RestResponse.fail(e.getMessage());
    }

    /**
     * 处理所有方法异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    RestResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        LOGGER.error(e.getMessage(), e);
        return RestResponse.fail(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

}
