package be.bstorm.tf_java2025_springmvc_exorecap.pl.validators;

import be.bstorm.tf_java2025_springmvc_exorecap.pl.models.dtos.user.RegisterForm;
import be.bstorm.tf_java2025_springmvc_exorecap.pl.validators.annotations.SamePassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SamePasswordValidator implements ConstraintValidator<SamePassword, RegisterForm> {

    @Override
    public boolean isValid(RegisterForm registerForm, ConstraintValidatorContext constraintValidatorContext) {
        return registerForm.getPassword().equals(registerForm.getConfirmPassword());
    }
}
