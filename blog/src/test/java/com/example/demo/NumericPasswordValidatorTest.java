package com.example.demo;

import com.example.demo.annotation.NumericPasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

 class NumericPasswordValidatorTest {

  private NumericPasswordValidator validator;

  @Mock private ConstraintValidatorContext constraintValidatorContext;

  @BeforeEach
   void setUp() {
    MockitoAnnotations.initMocks(this);
    validator = new NumericPasswordValidator();
  }

  @Test
   void testValidNumericPassword() {
    String validPassword = "123456";
    assertTrue(validator.isValid(validPassword, constraintValidatorContext));
  }

  @Test
   void testInvalidNonNumericPassword() {
    String invalidPassword = "abc123";
    assertFalse(validator.isValid(invalidPassword, constraintValidatorContext));
  }

  @Test
   void testNullPasswordIsValid() {
    assertTrue(validator.isValid(null, constraintValidatorContext));
  }
}
