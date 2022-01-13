package com.monitoring.log.event;

import com.monitoring.log.event.model.LogEvent;
import com.monitoring.log.event.model.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@link LogEventPairValidator}
 */
@RunWith(MockitoJUnitRunner.class)
public class LogEventPairValidatorTest {

    /**
     * Tests {@link LogEventPairValidator#arePair(LogEvent, LogEvent)}
     */
    @Test
    public void testArePair() {

        LogEventPairValidator logEventPairValidator = new LogEventPairValidator();

        assertFalse(logEventPairValidator.arePair(null, null));
        assertFalse(logEventPairValidator.arePair(null, new LogEvent()));
        assertFalse(logEventPairValidator.arePair(new LogEvent(), null));
        assertFalse(logEventPairValidator.arePair(new LogEvent(), new LogEvent()));
        assertFalse(logEventPairValidator.arePair(new LogEvent(null, State.FINISHED, null, null, 0L),
                new LogEvent(null, State.FINISHED, null, null, 0L)));

        assertTrue(logEventPairValidator.arePair(new LogEvent(null, State.STARTED, null, null, 0L),
                new LogEvent(null, State.FINISHED, null, null, 0L)));

    }

}