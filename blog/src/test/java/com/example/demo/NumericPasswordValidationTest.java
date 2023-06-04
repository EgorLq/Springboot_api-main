package com.example.demo;

import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class NumericPasswordValidationTest {

    @Autowired
    private Validator validator;

    @Test
    public void testValidNumericPassword() {
        User user = new User();
        user.setPassword("123456");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "No violations should be found");
    }

    @Test
    public void testInvalidNumericPassword() {
        User user = new User();
        user.setPassword("abc123");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(2, violations.size(), "One violation should be found");


    }

}
