package be.bstorm.tf_java2025_springmvc_exorecap.pl.validators.annotations;

import be.bstorm.tf_java2025_springmvc_exorecap.pl.validators.SamePasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = {SamePasswordValidator.class})
public @interface SamePassword {

    String message() default "Passwords do not match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
