package com.oit.oitcloud.config;

import com.oit.oitcloud.service.impl.ClientDetailsServiceImpl;
import com.oit.oitcloud.service.impl.TokenServiceImpl;
import com.oit.oitcloud.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @Description TODO
 * @Author guff
 * @Date 2020/5/27 16:51
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore redisTokenStore;

    //@Autowired
    //private TokenServiceImpl tokenServiceImpl;

    @Autowired
    private ClientDetailsServiceImpl clientDetailsServiceImpl;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private WebResponseExceptionTranslator bootWebResponseExceptionTranslator;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(redisTokenStore)
                 //实现登录互踢
                 //.tokenServices(tokenServiceImpl)
                 .userDetailsService(userDetailsServiceImpl)
                 .exceptionTranslator(bootWebResponseExceptionTranslator)
                 .authenticationManager(authenticationManager);

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsServiceImpl);
        //配置内存模式的客户端
//        clients.inMemory()
//                .withClient("demoApp")
//                .secret("demoAppSecret")
//                .authorizedGrantTypes("client_credentials", "password", "refresh_token")
//                .scopes("all")
//                .resourceIds("oauth2-resource")
//                .redirectUris("http://ums.oit.com")
//                .accessTokenValiditySeconds(60*2)
//                .refreshTokenValiditySeconds(60*60);
    }
}
