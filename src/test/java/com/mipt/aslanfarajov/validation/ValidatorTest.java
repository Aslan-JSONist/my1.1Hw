package com.mipt.aslanfarajov.validation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mipt.aslanfarajov.validation.model.User;

public class ValidatorTest {

    @Test
    void testValidObject() {
        User user = new User("Иван", "ivan@mail.ru", 25, "password123");
        ValidationResult result = Validator.validate(user);

        assertTrue(result.isValid());
        assertEquals(0, result.getErrors().size());
    }

    @Test
    void testNotNullValidation() {
        User user = new User(null, "test@mail.ru", 25, "password");
        ValidationResult result = Validator.validate(user);

        assertFalse(result.isValid());
        assertTrue(result.getErrors().contains("Имя не может быть null"));
    }

    @Test
    void testSizeValidation() {
        User user = new User("А", "test@mail.ru", 25, "123");
        ValidationResult result = Validator.validate(user);

        assertFalse(result.isValid());
        assertTrue(result.getErrors().contains("Имя должно быть от 2 до 50 символов"));
        assertTrue(result.getErrors().contains("Пароль должен быть от 6 до 20 символов"));
    }

    @Test
    void testRangeValidation() {
        User user = new User("Иван", "test@mail.ru", -5, "password123");
        ValidationResult result = Validator.validate(user);

        assertFalse(result.isValid());
        assertTrue(result.getErrors().contains("Возраст должен быть от 0 до 150"));
    }

    @Test
    void testEmailValidation() {
        User user = new User("Иван", "invalid-email", 25, "password123");
        ValidationResult result = Validator.validate(user);

        assertFalse(result.isValid());
        assertTrue(result.getErrors().contains("Некорректный формат email"));
    }

    @Test
    void testMultipleErrors() {
        User user = new User(null, "invalid", -1, "123");
        ValidationResult result = Validator.validate(user);

        assertFalse(result.isValid());
        assertEquals(4, result.getErrors().size());
    }

    @Test
    void testNullObject() {
        ValidationResult result = Validator.validate(null);
        assertFalse(result.isValid());
        assertTrue(result.getErrors().contains("Validated object cannot be null"));
    }
}
