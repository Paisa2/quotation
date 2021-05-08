package com.genesiscode.quotation.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.genesiscode.quotation.security.ResponsiblePermission.*;

public enum RoleResponsible {

    HEAD_OF_ADMINISTRATIVE_UNIT(Sets.newHashSet(WRITE_DIRECTORE_OF_EXPENSE_UNIT,
                                                WRITE_HEAD_OF_DIRECTORE_OF_EXPENSE_UNIT)),
    HEAD_OF_DEPENDENCY_ADMINISTRATIVE_UNIT(Sets.newHashSet()),
    HEAD_OF_DIRECTION(Sets.newHashSet()),
    HEAD_OF_EXPENSE_UNIT(Sets.newHashSet()),
    HEAD_OF_DEPENDENCY_EXPENSE_UNIT(Sets.newHashSet());

    private final Set<ResponsiblePermission> permissions;

    RoleResponsible(Set<ResponsiblePermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ResponsiblePermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
