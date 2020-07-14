package com.oit.oitcloud.event;

import com.oit.oitcloud.service.impl.UserDetailsServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        if(StringUtils.isBlank(username)){
            //throw new UsernameException("username不可以为空");
        }
        if(StringUtils.isBlank(password)){
            //throw new PasswordException("password不可以为空");
        }
        //获取用户信息
        UserDetails user = userDetailsServiceImpl.loadUserByUsername(username);
        //比较前端传入的密码明文和数据库中加密的密码是否相等
        if (!passwordEncoder.matches(password, user.getPassword())) {
            //发布密码不正确事件
            publisher.publishEvent(new UserLoginFailedEvent(authentication));
            //throw new PasswordException("password密码不正确");
        }
        //获取用户权限信息
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);

    }


    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}
