# Selenium Test Automation Framework

This repository contains a UI test automation framework for
[Automation Exercise](https://automationexercise.com/). It demonstrates a
maintainable Selenium project structure using the Page Object Model, TestNG,
reusable utilities, reporting, and CI support.

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

## Project Structure

```text
selenium-framework-project/
├── src/main/resources/
│   └── config.properties          # Application configuration
├── src/test/java/
│   ├── base/                      # WebDriver setup and teardown
│   ├── listeners/                 # TestNG listeners
│   ├── pages/                     # Page Object classes
│   ├── tests/
│   │   ├── auth/signup/           # Registration tests
│   │   └── smoke/                 # Smoke tests
│   └── utils/                     # Test data, reporting and helper classes
├── Jenkinsfile                    # Jenkins pipeline
├── pom.xml                        # Maven configuration
└── testng.xml                     # TestNG suite configuration
```

## Prerequisites

Install the following tools before running the tests:

- JDK 21 or later
- Maven 3.9 or later
- Docker, or a Selenium Grid instance with Chrome available

Verify the local installations:

```bash
java -version
mvn -version
docker --version
```

## Setup

Clone the repository and enter the project directory:

```bash
git clone <repository-url>
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

## Configuration

Application settings are stored in
`src/main/resources/config.properties`:

```properties
base.url=https://automationexercise.com/
title=Automation Exercise
```

Update these values when running the framework against a different environment.

## Test Reports and Screenshots

After a test run, the generated outputs are available at:

- Extent report: `test-output/ExtentReport.html`
- Failure screenshots: `test-output/screenshots/`
- Maven Surefire results: `target/surefire-reports/`

Generated reports and screenshots should not be committed to source control.

## Continuous Integration

The included `Jenkinsfile` provides a parameterized pipeline. The
`TEST_GROUP` parameter can run either the `smoke` or `auth` group. Jenkins also
publishes Surefire results and the Extent HTML report after execution.

The Jenkins agent must have Java, Maven, and access to a Selenium Grid instance.

## Design Approach

- Page Object classes keep locators and UI interactions separate from tests.
- Test classes describe scenarios and contain the assertions.
- Explicit waits are used instead of fixed delays.
- TestNG groups separate smoke and authentication coverage.
- Data providers support reusable negative test scenarios.
- Faker-generated values reduce conflicts between registration runs.

## Disclaimer

This project is intended for test automation practice and portfolio use. It is
not affiliated with Automation Exercise.
