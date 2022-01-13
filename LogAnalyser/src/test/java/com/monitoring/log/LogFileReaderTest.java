package com.monitoring.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@link LogFileReader}
 */
@RunWith(PowerMockRunner.class)
public class LogFileReaderTest {

    /**
     * Tests {@link LogFileReader#readLogFile(String)}
     */
    @Test
    public void testReadLogFile() {
        assertEquals(6, new LogFileReader().readLogFile("src/main/resources/logfile.txt").size());
    }

    /**
     * Tests {@link LogFileReader#readLogFile(String)} when {@link java.io.IOException thrown}
     */
    @Test
    public void testReadLogFile_IOException() {
        assertTrue(new LogFileReader().readLogFile("test").isEmpty());
    }
}
