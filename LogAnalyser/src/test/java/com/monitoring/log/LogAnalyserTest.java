package com.monitoring.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monitoring.log.database.EventRepository;
import com.monitoring.log.event.EventDurationCalculator;
import com.monitoring.log.event.EventGenerator;
import com.monitoring.log.event.LogEventPairValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests {@link LogAnalyser}
 * <p>
 * TODO - Add more tests
 */
@RunWith(MockitoJUnitRunner.class)
public class LogAnalyserTest {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private LogFileReader logFileReader;

    @Mock
    private LogEventPairValidator logEventPairValidator;

    @Mock
    private EventGenerator alertGenerator;

    @Mock
    private EventRepository eventRepository;

    /**
     * Tests {@link LogAnalyser#analyse(String, long)}
     */
    @Test
    public void testAnalyse() {
        String path = "src/main/resources/logfile.txt";

        new LogAnalyser(new ObjectMapper(), new LogFileReader(), new LogEventPairValidator(),
                new EventGenerator(new EventDurationCalculator()), eventRepository)
                .analyse(path, 2000);

        ArgumentCaptor<List> argument = ArgumentCaptor.forClass(List.class);
        verify(eventRepository).saveAll(argument.capture());
        assertEquals(3, argument.getValue().size());
    }

    /**
     * Tests {@link LogAnalyser#analyse(String, long)} when no log lines found
     */
    @Test
    public void testAnalyse_NoLogsFound() {
        String path = "test";

        when(logFileReader.readLogFile(path)).thenReturn(new ArrayList<>());

        new LogAnalyser(objectMapper, logFileReader, logEventPairValidator, alertGenerator, eventRepository)
                .analyse(path, 2000);

        verify(eventRepository, never()).saveAll(any());
    }

    /**
     * Tests {@link LogAnalyser#analyse(String, long)} when invalid log format
     */
    @Test
    public void testAnalyse_InvalidLogFormat() {
        String path = "test";

        when(logFileReader.readLogFile(path)).thenReturn(Collections.singletonList("test"));

        new LogAnalyser(new ObjectMapper(), logFileReader, logEventPairValidator, alertGenerator, eventRepository)
                .analyse(path, 2000);

        verify(eventRepository, never()).saveAll(any());
    }
}
