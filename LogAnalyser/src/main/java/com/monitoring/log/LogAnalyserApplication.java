package com.monitoring.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application that analyses logs and writes events to a database
 */
@SpringBootApplication
public class LogAnalyserApplication implements CommandLineRunner {

    private static final Logger LOGGER = LogManager.getLogger(LogAnalyserApplication.class);

    private final LogAnalyser logAnalyser;

    /**
     * Initialises a new {@link LogAnalyserApplication}
     *
     * @param logAnalyser A {@link LogAnalyser}
     */
    public LogAnalyserApplication(LogAnalyser logAnalyser) {
        this.logAnalyser = logAnalyser;
    }

    /**
     * Starting point of application
     *
     * @param args Application arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(LogAnalyserApplication.class, args);
    }

    /**
     * Runs the log analysis
     *
     * @param args Application arguments
     */
    @Override
    public void run(String... args) {
        // TODO - Add ability to pass these parameters in the command line arguments
        // TODO - Add ability to specify where the database should be written to
        String path = "src/main/resources/logfile.txt";
        long threshold = 4;

        LOGGER.info("Running analysis of log file in path " + path + " with alert threshold " + threshold + "ms");
        this.logAnalyser.analyse(path, threshold);
    }

}