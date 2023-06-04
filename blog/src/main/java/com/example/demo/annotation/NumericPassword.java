package com.example.demo.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NumericPasswordValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumericPassword {
  String message() default "Поле password должно содержать только цифры";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
