package com.oit.oitcloud.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 用户登录失败监听器
 */
@Component
public class UserLoginFailedListener implements ApplicationListener<UserLoginFailedEvent> {
    @Override
    public void onApplicationEvent(UserLoginFailedEvent event) {
        System.out.println("---用户验证信息failed---");
    }
}
