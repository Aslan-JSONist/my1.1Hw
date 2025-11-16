package com.mipt.aslanfarajov.patterns;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

public class CachingDecoratorTest {
    @Test
    void testCaching() {
        SimpleDataService simpleService = new SimpleDataService();
        CachingDecorator cachingService = new CachingDecorator(simpleService);

        cachingService.saveData("key1", "data1");
        assertEquals("data1", cachingService.findDataByKey("key1").get());

        simpleService.deleteData("key1");
        assertEquals("data1", cachingService.findDataByKey("key1").get());

        cachingService.deleteData("key1");
        assertFalse(cachingService.findDataByKey("key1").isPresent());
    }
}
