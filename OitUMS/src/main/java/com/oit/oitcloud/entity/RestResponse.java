package com.oit.oitcloud.entity;

import com.oit.oitcloud.enums.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "返回结果")
@Data
public class RestResponse implements Serializable {
    @ApiModelProperty(value = "状态码" ,example = "1")
    private int code;
    @ApiModelProperty(value = "描述" ,example = "message")
    private String message;
    @ApiModelProperty(value = "返回数据" ,example = "{}")
    private Object data;

    public static RestResponse succuess(){
        RestResponse restResponse = new RestResponse();
        restResponse.setResultCode(ResultCode.SUCCESS);

        return restResponse;
    }

    public static RestResponse succuess(Object data){
        RestResponse restResponse = new RestResponse();
        restResponse.setResultCode(ResultCode.SUCCESS);
        restResponse.setData(data);

        return restResponse;
    }

    public static RestResponse fail() {
        RestResponse restResponse = new RestResponse();
        restResponse.setResultCode(ResultCode.FAIL);

        return restResponse;
    }


    public static RestResponse fail(ResultCode resultCode) {
        RestResponse restResponse = new RestResponse();
        restResponse.setResultCode(resultCode);

        return restResponse;
    }

    public static RestResponse fail(String message) {
        RestResponse restResponse = new RestResponse();
        restResponse.setCode(ResultCode.FAIL.getCode());
        restResponse.setMessage(message);

        return restResponse;
    }

    public static RestResponse fail(Integer code, String message) {
        RestResponse restResponse = new RestResponse();
        restResponse.setCode(code);
        restResponse.setMessage(message);

        return restResponse;
    }

    public static RestResponse fail(ResultCode resultCode, Object data) {
        RestResponse restResponse = new RestResponse();
        restResponse.setResultCode(resultCode);
        restResponse.setData(data);

        return restResponse;
    }

    private void setResultCode(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

}
