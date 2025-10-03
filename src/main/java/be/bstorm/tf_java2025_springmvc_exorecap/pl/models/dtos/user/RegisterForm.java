package be.bstorm.tf_java2025_springmvc_exorecap.pl.models.dtos.user;

import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.User;
import be.bstorm.tf_java2025_springmvc_exorecap.pl.validators.annotations.SamePassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SamePassword
public class RegisterForm {

    @NotBlank @Email @Size(max = 150)
    private String email;

    @NotBlank @Size(max = 50)
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

    public User ToUser() {
        return new User(email, login, password);
    }
}
