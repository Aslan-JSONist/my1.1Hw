package com.mipt.aslanfarajov.patterns;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

public class LoggingDecoratorTest {
    @Test
    void testLogging() {
        SimpleDataService simpleService = new SimpleDataService();
        LoggingDecorator loggingService = new LoggingDecorator(simpleService);

        loggingService.saveData("key1", "data1");
        Optional<String> result = loggingService.findDataByKey("key1");
        boolean deleted = loggingService.deleteData("key1");

        assertTrue(result.isPresent());
        assertTrue(deleted);
    }
}
