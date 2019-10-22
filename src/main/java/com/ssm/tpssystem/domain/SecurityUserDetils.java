package com.ssm.tpssystem.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SecurityUserDetils extends User implements UserDetails {
    private static final long serialVersionUID = 1L;

    public SecurityUserDetils(User user) {
        if(user!=null){
            this.setId(user.getId());
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setDuty(user.getDuty());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //TODO:Access Control
        return AuthorityUtils.commaSeparatedStringToAuthorityList("");
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
