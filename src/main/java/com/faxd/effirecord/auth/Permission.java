package com.faxd.effirecord.auth;

import com.faxd.effirecord.constant.PermissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements GrantedAuthority {
    private Long entityId;
    private String entityType;
    private PermissionType permission;
    private String authority;
    @Override
    public String getAuthority() {
        return entityType + ":" + permission;
    }
}
