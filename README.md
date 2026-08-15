# Selenium Test Automation Framework

This repository contains a UI test automation framework for
[Automation Exercise](https://automationexercise.com/).

## Test Coverage

The current test suite includes:

- Homepage smoke tests
- Homepage product visibility checks
- Navigation menu and link validation
- Positive user registration
- Data-driven negative registration scenarios
- Screenshot capture when a test fails

## Technologies

- Java 21
- Selenium WebDriver 4
- TestNG
- Maven
- Selenium Grid / Remote WebDriver
- DataFaker
- ExtentReports
- SLF4J and Logback
- Jenkins

## Prerequisites

- Java 21
- Maven
- Docker

## Setup

Clone the repository and enter the project directory:

```bash
git clone https://github.com/busekonmazkale/selenium-framework-project.git
cd selenium-framework-project
```

Start a standalone Selenium Chrome container. The framework currently connects
to Selenium at `http://localhost:4444`:

```bash
docker run --rm -d \
  --name selenium-chrome \
  -p 4444:4444 \
  --shm-size="2g" \
  selenium/standalone-chrome:latest
```

Confirm that Selenium is ready by opening
[http://localhost:4444/ui](http://localhost:4444/ui).

## Running Tests

Run the complete TestNG suite:

```bash
mvn clean test
```

Run only smoke tests:

```bash
mvn test -Dgroups=smoke
```

Run only authentication tests:

```bash
mvn test -Dgroups=auth
```

Run a specific test class:

```bash
mvn test -Dtest=tests.auth.signup.SignupPositiveTest
```

Stop the Selenium container after the tests finish:

```bash
docker stop selenium-chrome
```