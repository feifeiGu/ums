package com.oit.oitcloud.exception;

import com.oit.oitcloud.enums.ResultCode;

public class BusinessException extends RuntimeException {
    private Integer errorId;

    public BusinessException(String message){
        super(message);
        this.errorId = ResultCode.FAIL.getCode();
    }

    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.errorId = resultCode.getCode();
    }
}
