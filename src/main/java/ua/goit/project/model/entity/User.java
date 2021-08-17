package ua.goit.project.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.goit.project.dto.enums.Role;
import ua.goit.project.dto.enums.UserStatus;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    @NotEmpty
    @Column(name = "user_email", unique = true)
    private String userEmail;
    @NotEmpty
    @Column(name = "user_password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, userEmail);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return userId == user.userId &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                userEmail.equals(user.userEmail);
    }
}
