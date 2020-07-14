package com.oit.oitcloud.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * APP类型
 */
@AllArgsConstructor
@NoArgsConstructor
public enum AppType {
    //APP应用类型
    PLATFORM(1, "壹站平台端"),
    THIRDPARTY(2, "壹站三方端"),
    SERVICE(3, "壹站服务商端"),
    CARRIER(4, "壹站承运商端");

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
