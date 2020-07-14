package com.oit.oitcloud.service.impl;

import com.oit.oitcloud.constants.OitUmsConstants;
import com.oit.oitcloud.entity.OitRoleResource;
import com.oit.oitcloud.entity.OitUser;
import com.oit.oitcloud.entity.OitUserResource;
import com.oit.oitcloud.entity.OitUserRoleRel;
import com.oit.oitcloud.enums.ResultCode;
import com.oit.oitcloud.exception.BusinessException;
import com.oit.oitcloud.service.OitRoleResourceService;
import com.oit.oitcloud.service.OitUserResourceService;
import com.oit.oitcloud.service.OitUserRoleRelService;
import com.oit.oitcloud.service.OitUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private OitUserService oitUserService;

    @Autowired
    private OitUserResourceService oitUserResourceService;

    @Autowired
    private OitUserRoleRelService oitUserRoleRelService;

    @Autowired
    private OitRoleResourceService oitRoleResourceService;

    /**
     * 查询数据库用户权限
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.isEmpty()){
            throw new BusinessException(ResultCode.USERNAME_IS_NULL);
        }
        OitUser oitUser = oitUserService.queryByUsername(username);
        if(oitUser == null){
            throw new BusinessException(ResultCode.USER_LOGIN_ERROR);
        }
        if(oitUser.getStatus().equals(OitUmsConstants.DISABLE)){
            throw new BusinessException(ResultCode.USER_DISABLE);
        }
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("USER"));
        authorityList.add(new SimpleGrantedAuthority("TYPE-"+oitUser.getUserType()));
        //设置用户资源权限
        OitUserResource userResource = new OitUserResource();
        userResource.setUserId(oitUser.getId());
        List<OitUserResource> oitUserResourceList = oitUserResourceService.queryAll(userResource);
        for(OitUserResource oitUserResource : oitUserResourceList){
            if(!oitUserResource.getResourceAuth().isEmpty()){
                String[] authCodes = oitUserResource.getResourceAuth().split(",");
                for(String auth : authCodes){
                    if(!auth.isEmpty()){
                        authorityList.add(new SimpleGrantedAuthority(auth));
                    }
                }
            }
        }
        //设置用户角色资源权限
        OitUserRoleRel userRoleRel = new OitUserRoleRel();
        userRoleRel.setUserId(oitUser.getId());
        List<OitUserRoleRel> oitUserRoleRelList = oitUserRoleRelService.queryAll(userRoleRel);
        for(OitUserRoleRel oitUserRoleRel : oitUserRoleRelList){
            OitRoleResource roleResource = new OitRoleResource();
            roleResource.setRoleId(oitUserRoleRel.getRoleId());
            List<OitRoleResource> oitRoleResourceList = oitRoleResourceService.queryAll(roleResource);
            for(OitRoleResource oitRoleResource : oitRoleResourceList){
                if(!oitRoleResource.getResourceAuth().isEmpty()) {
                    String[] authCodes = oitRoleResource.getResourceAuth().split(",");
                    for (String auth : authCodes) {
                        if(!auth.isEmpty()) {
                            authorityList.add(new SimpleGrantedAuthority(auth));
                        }
                    }
                }
            }
        }
        //返回认证用户
        return new User(oitUser.getUsername(), oitUser.getPassword(), authorityList);
    }
}
