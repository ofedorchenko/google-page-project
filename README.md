# Sample Selenium test running in docker container 

The project demonstrates how to run Selenium tests in a docker container.

## Run in docker container

This way will fit for a CI/CD pipeline.

```
docker build -t selenium-test .

docker run --rm -v <path-to-local-reports>:/app/reports selenium-test
```

The result of tests can be viewed in `<path-to-local-reports>/html/index.html`

## How to run the tests locally

This way will fit if you need to see how tests run.

1. Download chromedriver for the current version of Chrome - https://googlechromelabs.github.io/chrome-for-testing/
2. Extract and put the file "chromedriver.exe" somewhere on your computer
3. Configure TestNG test run with Environment variable `CHROMEDRIVER_PATH=<path-to-chromedriver.exe>` and use `testng-suite.xml` to run test methods.