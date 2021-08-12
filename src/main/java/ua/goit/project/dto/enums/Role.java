package ua.goit.project.dto.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public static Optional<Role> getRole(String role) {
        return Arrays.stream(Role.values())
                .filter(value -> value.getRole().equals(role))
                .findAny();
    }

    public String getRole() {
        return role;
    }

}
