package com.whx.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户登录类
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel("LoginUser对象")
public class LoginUser implements Serializable, UserDetails {

    /**
     * 用户类
     */
    @ApiModelProperty("用户信息")
    private User user;

    /**
     * 权限列表
     */
    @ApiModelProperty("用户权限")
    private List<String> permissions;

    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    public LoginUser(User user,List<String> permissions){
        this.user=user;
        this.permissions=permissions;
    }

    /**
     * 将permissions中String类型的权限信息封装成SimpleGrantedAuthority对象
     * @return 权限集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //把permissions中String类型的权限信息封装成SimpleGrantedAuthority对象
        if (authorities!=null){
            return authorities;
        }
        authorities=permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
