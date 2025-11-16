package com.mipt.aslanfarajov.patterns;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

public class MetricableDecoratorTest {
    @Test
    void testMetrics() {
        SimpleDataService simpleService = new SimpleDataService();
        MetricableDecorator metricService = new MetricableDecorator(simpleService);

        metricService.saveData("key1", "data1");
        Optional<String> result = metricService.findDataByKey("key1");
        boolean deleted = metricService.deleteData("key1");

        assertTrue(result.isPresent());
        assertTrue(deleted);
    }
}
