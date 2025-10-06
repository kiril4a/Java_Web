package com.example.Java_Web.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CosmicWordValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CosmicWordCheck {
    String message() default "Name must contain cosmic words like star, galaxy, comet";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
