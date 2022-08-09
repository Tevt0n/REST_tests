# Automated API tests for [Reqres](http://reqres.in/) and [Demo Web Shop](http://demowebshop.tricentis.com/)

## :page_with_curl: Content

➠ [Technology stack](#TechnologyStack)

➠ [Launch from the terminal](#Terminal)

➠ [Jenkins build](#Jenkins)

➠ [Allure Report integration](#Report)

➠ [Allure TestOps integration](#TestOps)

➠ [Video examples](#Video)

## :computer: <a name="TechnologyStack"></a> Technology stack

<p align="center">
<img width="6%" title="Java" src="images/logo/Java.svg">
<img width="6%" title="IntelliJ IDEA" src="images/logo/Intelij_IDEA.svg">
<img width="6%" title="JUnit5" src="images/logo/JUnit5.svg">
<img width="6%" title="Selenide" src="images/logo/Selenide.svg">
<img width="5%" title="RestAssured" src="images/logo/RestAssured.svg">
<img width="6%" title="Jenkins" src="images/logo/Jenkins.svg">
<img width="6%" title="Gradle" src="images/logo/Gradle.svg">
<img width="6%" title="GitHub" src="images/logo/GitHub.svg">
<img width="6%" title="Selenoid" src="images/logo/Selenoid.svg">
<img width="6%" title="Allure Report" src="images/logo/Allure_Report.svg">
<img width="6%" title="Allure TestOps" src="images/logo/Allure_TO.svg">
</p>

## :technologist: <a name="Terminal"></a> Launch from the terminal

### Local test run:
```
gradle clean test (to run all tests) 
gradle clean reqres (to run reqres tests only)
gradle clean demowebshop (to run demowebshop tests only)
```

### Run tests on remote selenoid server:
```
gradle clean test -Dconfig=RemoteConfig
```

### Additional build parameters you could use 

>
> <code>-Dbrowser</code> – to change browser, by default it's Chrome. 
>
> <code>-DbrowserVersion</code> – to change browser version, by default it's the last available.
>
> <code>-DbrowserSize</code> – to change browser size, by default it's 1920x1080.
>
> <code>-DbaseUrl</code> – to change base url for the tests, by default it's http://demowebshop.tricentis.com/
>
> <code>-DselenoidUrl</code> – to change selenoid server on which the tests are going to run.

## <a name="Jenkins"></a><img width="4%" title="Jenkins" src="images/logo/Jenkins.svg"> Jenkins [build](https://jenkins.autotests.cloud/job/REST_tests/)

<p align="center">
  <img src="images/screenshots/Jenkins.png">
</p>

### :robot: Build Options

<p align="center">
  <img src="images/screenshots/BuildOptions.png">
</p>

## <a name="Report"></a><img width="4%" title="Allure Report" src="images/logo/Allure_Report.svg"> Allure [Report](https://jenkins.autotests.cloud/job/REST_tests/allure/)

### Overview

<p align="center">
<img title="Allure reports Overview tab screenshot" src="images/screenshots/AR-main_page.png">
</p>

### Test Suites

<p align="center">
<img title="Allure reports Test suites tab screenshot" src="images/screenshots/AR-test_cases.png">
</p>

## <a name="TestOps"></a><img width="4%" title="Allure TestOps" src="images/logo/Allure_TO.svg"> Allure [TestOps](https://allure.autotests.cloud/project/678/)

### Dashboards

<p align="center">
<img title="Allure TestOps Dashboard screenshot" src="images/screenshots/ATO-dashboards.png">
</p>

### Test cases

<p align="center">
<img title="Allure Test cases Dashboard screenshot" src="images/screenshots/ATO-test_cases.png">
</p>

### Launch

<p align="center">
<img title="Allure TestOps Launch screenshot" src="images/screenshots/ATO-launch.png">
</p>


## <a name="Video"></a> :tv: Video attach example
<p align="center">
<img src="images/gif/Test_video.gif" alt="video" width="600">
</p>
