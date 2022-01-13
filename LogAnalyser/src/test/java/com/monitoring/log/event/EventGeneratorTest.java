package com.monitoring.log.event;

import com.monitoring.log.event.model.LogEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Tests {@link EventGeneratorTest}
 */
@RunWith(MockitoJUnitRunner.class)
public class EventGeneratorTest {

    @Mock
    private EventDurationCalculator eventDurationCalculator;

    /**
     * Tests {@link EventGenerator#generate(LogEvent, LogEvent, long)}
     */
    @Test
    public void testGenerate() {
        when(eventDurationCalculator.calculate(any(), any())).thenReturn(100L);

        assertNotNull(new EventGenerator(eventDurationCalculator).generate(
                new LogEvent(null, null, null, null, 0L),
                new LogEvent(null, null, null, null, 0L),
                2000));
    }

}