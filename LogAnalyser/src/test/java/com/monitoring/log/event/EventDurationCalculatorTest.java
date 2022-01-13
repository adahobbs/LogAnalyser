package com.monitoring.log.event;

import com.monitoring.log.event.model.LogEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@link EventDurationCalculator}
 */
@RunWith(MockitoJUnitRunner.class)
public class EventDurationCalculatorTest {

    /**
     * Tests {@link EventDurationCalculator#calculate(LogEvent, LogEvent)}
     */
    @Test
    public void testCalculate() {
        long timestamp1 = 1640739600;
        long timestamp2 = 1640739610;

        assertEquals(new EventDurationCalculator().calculate(
                        new LogEvent(null, null, null, null, timestamp1),
                        new LogEvent(null, null, null, null, timestamp2)),
                timestamp2 - timestamp1);
    }

}