# Archetype for fonctional UI testing with Selenium 

## Page Object Model & Page Factory integrate with Selenium in Maven TestNG project 

It provides following features:

* Parameterization and externalization of variables (Data driven approach)
* Page Object Model & Page Factory to create maintainable, reusable testcases
* Maven & TestNG to automatically execute the testcases
* Running testcases on multiple browsers (chrome, firefox, edge, opera) 
* Apache Log4j to log activities
* TestNG to generate test case result reports 

### Requirements

* Apache Maven with v3.8.4
* JDK with v8

### Usage:

* To change or add more data, you simply modify the excel file "data.xlsx" defined under the folder "files"
* To change the browser, you simply modify the parameter "browserName" defined in testNG file "testng.xml"
* The variable "url" is defined under the test resources folder in specific file "config.properties"

The definition of any variable must be defined with this format 'key=value'


### Execution

* To execute this project:
mvn clean test -DtestngFile=testng.xml : goal used to execute testcases and generate testNG reports ('/test-output/index.html')

### Note
There is a manual interaction to bypass the google reKAPTCHA 

 
