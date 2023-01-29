
# Singtel_Assignment_Web_UI
Test Automation assignment for UI web tests

Note the framework is designed using BDD Cucumber in java.
The Page Object Model and PageFactory has been implemented.

## Precondition for working on the e2e tests
1. Project should be setup in  java IDE
2. Build the tests - Singtel_Assignment_Web_UI project
     mvn clean install -DskipTests

## Running the test locally
1. Make sure the application is built correctly. (refer above)
2. Run test with the IDE as follows,
  * Import maven dependencies in your IDE
  * Open src/test/java/runner.....the test you would like to execute.
  * Run test with your IDE:
        edit VM options : -Dbrowser=CHROME

3. Run the tests using the mvn command as follows,
  e.g. Supported browser= Chrome
   * mvn clean test -U -Dbrowser=chrome -Dtest=Runner

4. Run the test on the selenium grid using the mvn command as follows,
  e.g. mvn clean test -U -Dbrowser=chrome -DurlSeleniumHub="$HUB_URL_ADDRESS" -Dtest="$Class"

## Reporting

### Extent Reports
Reporting using extent reports is implemented. The report would be generated at path
***target/cucumber-reports/report.html***

## Logging

For logging, we have implemented using log4j maven dependencies with the help of Logger class.





   
