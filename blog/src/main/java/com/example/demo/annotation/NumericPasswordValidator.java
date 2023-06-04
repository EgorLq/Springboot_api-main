package com.example.demo.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumericPasswordValidator implements ConstraintValidator<NumericPassword, String> {
  @Override
  public void initialize(NumericPassword constraintAnnotation) {
    // Метод initialize используется для инициализации валидатора.
    // В данном случае, аннотация NumericPassword не требует дополнительных параметров или настроек,
    // поэтому метод остается пустым.
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true; // Разрешаем null значения
    }
    return value.matches("\\d+"); // Проверяем, что значение состоит только из цифр
  }
}
