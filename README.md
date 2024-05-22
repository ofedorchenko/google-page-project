**To start the project you need to:**

1. Download the project

2. Download chromedriver for the current version of Chrome - https://googlechromelabs.github.io/chrome-for-testing/

3. Extract and put the file "chromedriver.exe" in the root of the "C" drive on your computer

4. Use testng-suite.xml to run test methods

Two methods will be run (one at a time). Both of them check the name of the Google logo.
One checks the correct name - successful, the other checks the wrong name - unsuccessful.

--------------------------------------------------------------------------------------------------------------------

**Example of using Docker commands**

docker build -t selenium-test .

docker run --rm -v /path/to/local/reports:/app/reports selenium-test

--------------------------------------------------------------------------------------------------------------------

The result of tests can be viewed locally in **/path/to/local/reports/html/index.html** 