package com.faxd.effirecord.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Builder
@Getter
public class EffiRecordUserDetails implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private Collection<Permission> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public void clearPassword(){
        this.password = null;
    }
    public EffiRecordUserDetails(){}

    public EffiRecordUserDetails(Long id, String username, String password, Collection<Permission> authorities, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = isAccountNonExpired;
        this.accountNonLocked = isAccountNonLocked;
        this.credentialsNonExpired = isCredentialsNonExpired;
        this.enabled = isEnabled;
    }
}
