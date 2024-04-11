package com.carbon.thephantasyrpg.enums;

import lombok.Getter;

/**
 * Represents the different roles that a user can have.
 */
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

    /**
     * Returns the Role with the given ID.
     * @param id The ID of the Role to return.
     * @return The Role with the given ID.
     */
    public static Roles valueOfId(long id) {
        for (Roles role : values()) {
            if (role.getId() == id) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid Role ID: " + id);
    }

    /**
     * Returns the Role with the given role name.
     * @param roleName The role name of the Role to return.
     * @return The Role with the given role name.
     */
    public static Roles valueOfRoleName(String roleName) {
        for (Roles role : values()) {
            if (role.getRoleName().equals(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid Role Name: " + roleName);
    }
}

