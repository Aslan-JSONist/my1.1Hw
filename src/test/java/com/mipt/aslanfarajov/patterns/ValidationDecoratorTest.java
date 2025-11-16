package com.mipt.aslanfarajov.patterns;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

public class ValidationDecoratorTest {
    @Test
    void testValidation() {
        SimpleDataService simpleService = new SimpleDataService();
        ValidationDecorator validationService = new ValidationDecorator(simpleService);

        assertThrows(IllegalArgumentException.class, () -> validationService.findDataByKey(""));
        assertThrows(IllegalArgumentException.class, () -> validationService.saveData("", "data"));
        assertThrows(IllegalArgumentException.class, () -> validationService.saveData("key", ""));
        assertThrows(IllegalArgumentException.class, () -> validationService.deleteData(""));

        validationService.saveData("valid", "valid");
        assertEquals("valid", validationService.findDataByKey("valid").get());
    }
}
