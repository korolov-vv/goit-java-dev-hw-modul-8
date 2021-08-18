package ua.goit.project.model;

import java.util.Arrays;
import java.util.Optional;

public enum Role {
    ROLE_ADMIN("ROLE_ADMIN", "Admin"),
    ROLE_USER("ROLE_USER", "User");

    private String role;
    private String roleForDisplaying;

    Role(String role, String roleForDisplaying) {
        this.role = role;
        this.roleForDisplaying = roleForDisplaying;
    }

    public static Optional<Role> getRole(String role) {
        return Arrays.stream(Role.values())
                .filter(value -> value.getRole().equals(role))
                .findAny();
    }

    public String getRole() {
        return role;
    }

    public String getRoleForDisplaying() {
        return roleForDisplaying;
    }
}
