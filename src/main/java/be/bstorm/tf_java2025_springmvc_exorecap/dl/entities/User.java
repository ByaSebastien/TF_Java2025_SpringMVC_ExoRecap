package be.bstorm.tf_java2025_springmvc_exorecap.dl.entities;

import be.bstorm.tf_java2025_springmvc_exorecap.dl.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true, of = {"email", "login"})
@EqualsAndHashCode(callSuper = true, of = {"email", "login"})
@Table(name = "USER_")
public class User extends BaseEntity<Long> implements UserDetails {

    @Column(unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 50)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public User(String email, String login, String password, UserRole role) {
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return getEmail();
    }
}