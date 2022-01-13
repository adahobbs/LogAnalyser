package com.monitoring.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads a log files
 */
public class LogFileReader {

    private static final Logger LOGGER = LogManager.getLogger(LogFileReader.class);

    /**
     * Reads a log file from a provided {@code path}
     *
     * @param path Path to log file
     * @return Log lines as a list or an empty list if issues reading the file
     */
    public List<String> readLogFile(String path) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(new File(path).toPath());
        } catch (IOException e) {
            LOGGER.error("Error reading log file with path: " + path, e);
        }
        return lines;
    }

}