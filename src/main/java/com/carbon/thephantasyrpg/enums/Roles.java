package com.carbon.thephantasyrpg.enums;

import lombok.Getter;

@Getter
public enum Roles {
    USER(1L, "ROLE_USER"),
    ADMIN(2L, "ROLE_ADMIN");

    private final long id;
    private final String roleName;

    Roles(long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public static Roles valueOfId(long id) {
        for (Roles role : values()) {
            if (role.getId() == id) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid Role ID: " + id);
    }

    public static Roles valueOfRoleName(String roleName) {
        for (Roles role : values()) {
            if (role.getRoleName().equals(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid Role Name: " + roleName);
    }
}

