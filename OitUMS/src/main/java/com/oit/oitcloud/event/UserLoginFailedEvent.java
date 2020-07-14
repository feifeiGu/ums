package com.oit.oitcloud.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.security.core.Authentication;

/**
 * 用户登录失败事件
 */
public class UserLoginFailedEvent extends ApplicationEvent {
    public UserLoginFailedEvent(Authentication authentication) {
        super(authentication);
    }
}
