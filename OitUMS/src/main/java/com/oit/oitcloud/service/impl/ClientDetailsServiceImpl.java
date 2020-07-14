package com.oit.oitcloud.service.impl;

import com.oit.oitcloud.entity.OitApp;
import com.oit.oitcloud.service.OitAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    private OitAppService oitAppService;

    /**
     * 查询数据库客户端配置
     * @param clientId
     * @return
     * @throws ClientRegistrationException
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        BaseClientDetails baseClientDetails = new BaseClientDetails();
        OitApp oitApp = oitAppService.queryByAppKey(clientId);
        if(oitApp == null){
            throw new NoSuchClientException("clientId:" + clientId + "is not exist!");
        }
        //ClientId and ClientSecret
        baseClientDetails.setClientId(oitApp.getAppKey());
        baseClientDetails.setClientSecret(oitApp.getAppSecret());
        //授权
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("READ"));
        authorities.add(new SimpleGrantedAuthority("WRITE"));
        baseClientDetails.setAuthorities(authorities);
        //授权模式
        Set<String> authorizedGrantTypes = new TreeSet<String>();
        authorizedGrantTypes.add("password");
        authorizedGrantTypes.add("client_credentials");
        authorizedGrantTypes.add("refresh_token");
        baseClientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);
        Set<String> scopes = new TreeSet<String>();
        scopes.add("all");
        baseClientDetails.setScope(scopes);
        //AccessToken and RefreshToken有效期
        baseClientDetails.setAccessTokenValiditySeconds(60*60*24);
        baseClientDetails.setRefreshTokenValiditySeconds(60*60*24*30);
        return baseClientDetails;
    }
}
