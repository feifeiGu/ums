package com.oit.oitcloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @Description TODO
 * @Author guff
 * @Date 2020/5/28 10:54
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.requestMatchers()
                .antMatchers("/oitApp/**","/oitUser/**","/oitUserResource/**","/oitUserRoleRel/**","/oitRole/**","/oitRoleResource/**","/oitResource/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oitApp/**","/oitUser/**","/oitUserResource/**","/oitUserRoleRel/**","/oitRole/**","/oitRoleResource/**","/oitResource/**")
                .authenticated();
    }
}
