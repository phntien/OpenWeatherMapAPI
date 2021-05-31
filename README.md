NAB - VDC: TEST FOR OPEN WEATHER MAP API
==============
-------------- Java - Rest Assured - JUnit - TestNG - Maven - POM -------------

Prerequisite
--------------
- Install JDK 1.8
- Install maven - https://mkyong.com/maven/install-maven-on-mac-osx/
- Install IntelliJ Community Idea: https://www.jetbrains.com/idea/download/#section=mac


Running test
--------------

**Run by Command Line:**
Go to your project directory from terminal and hit following commands
* `mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/TestSuiteAPI.xml` to run Test Suite API feature.

**Run by TestNG**
+ Run All Suite API 
1. Right click the Project
2. Click on Maven
3. Click on Reimport
4. Right click on src/test/resources/TestSuiteAPI.xml
5. Click on Run 

+ Run single test script 
1. Right click the Project
2. Click on Maven
3. Click on Reimport
4. Right click 1 test script in: src/test/java/com/currentweather/api
5. Click on Run 

Framework Architecture
--------------
	OpenWeatherMapAPI
		|
		|_src/main/java/com/currentweather
		|	|_pages
		|	|   |_BaseTest.class
		|	|   |_DataProviderFactory.class	
		|	|_util
		|       |_Constants.class
		|       |_ExcelHelper.class
		|       |_ExceptionHelper.class
		|       |_LogHelper.class		
		|       
		|_src/test/
		|   |_java
		|      |_com/currentweather/api
    	|	|      |_api test script.class
		|	|_resources
    	|		|_data
    	|		|  |_TestData.json  
    	|		|testdata.api.weather
    	|		|  |_Testdata.xlsx
    	|       |_TesSuieAPI.xml

* **src/main/java/pages** - all the Pages in POM goes here.

* **src/main/java/util** - Utilize functions

* **src/test/java** - All api test scripts in here.

* **src/test/resources** - This package contains test data (file .json, .xlsx...)

* **src/test/resources/TestSuiteAPI.xml** - To run by TestNG

Test Report after running
--------------

+ Currently, I run test scripts by TestNG so the report is stored in "test-output/emailable-report.html". Please run it and open it in the browser to view the report  
+ If more time, I will apply plugin 'Report Portal' or 'Allure Report' for more visual UI
+ When running API Suite, I found 1 bug by automation script. Please refer the bug details into 'logbug.txt' for more information.

Data Driven 
-----------------
By using an excel file to store data, I can run test scripts by data-driven more quickly, more efficiently, and without much coding.

The predefined data in excel file in here **/src/test/resources** combine defined data in json file

Note that: I just put some valid and invalid data into excel file to demo data-driven 



