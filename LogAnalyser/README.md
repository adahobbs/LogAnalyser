# Log Analyser Coding Challenge Solution

## Summary

The application successfully achieves the objectives of the challenge:
* Take the path to logfile.txt as an input argument
  * Log Analyser is able to take a path parameter as input
* Parse the contents of logfile.txt
  * Log Analyser is able to parse the contents of a log file
* Flag any long events that take longer than 4ms
  * Log Analyser is able to flag events that take longer than a threshold (default is 4ms)
* Write the found event details to file-based HSQLDB (http://hsqldb.org/) in the working folder 
  * Log Analyser writes the processed contents of logfile.txt and outputs into a file-based HSQLDB
  
### Improvements

With more time I would have
* Added more unit tests to cover all use cases
* Concurrently analysed the extracted log lines
* Inserted into the database in chunks to reduce memory consumption
* Added ability to read command line arguments containing input log file path, alert threshold and output directory for database

## Tests

A Test-Driven approach was taken for certain components where unit tests can be found in src\test\java\com\monitoring\log\

## Approach

Technologies:
* Java 8
* Spring
* HSQLDB
* Maven
* Log4j
* JUnit
* Mockito
* PowerMock

Spring Boot was used as it is easy to setup and use, and enabled me to use Dependency Injection.

I decided to use Spring Integration for the HSQLDB component as I had used the framework previously with other database technologies.

JUnit, Mockito and PowerMock were used for testing the solution.

JavaDocs have been used to understand the structure and logic of the solution.