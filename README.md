# APIFirstTest

Simple Maven project for API-first testing using RestAssured and JUnit 5.

## Project structure

- pom.xml — Maven configuration (RestAssured, JUnit, Hamcrest)
- src/test/java/org/example/LoginTest.java — Login test example
- Other tests and helpers under src/test/java/**

## Prerequisites

- Java 21 (JDK)
- Maven 3.8+
- Network access to the target API

## Run tests

From the project root run:

mvn test

To run only the login test method:

mvn -Dtest=org.example.LoginTest#loginWithEmailAndPassword test

## Login test details

- Endpoint: https://www.ndosiautomation.co.za/API/login
- Payload (example, do not store credentials in source):
  {
    "email": "<YOUR_EMAIL>",
    "password": "<YOUR_PASSWORD>"
  }

Important: do not keep real credentials in source code. Configure secrets using one of the approaches below.

## Configuring credentials

Options to avoid hardcoding credentials in tests:

- Environment variables (recommended):
  - Set API_EMAIL and API_PASSWORD in your environment and modify LoginTest to read them:
    - System.getenv("API_EMAIL") / System.getenv("API_PASSWORD")

- Maven system properties:
  - mvn -Dapi.email=you@example.com -Dapi.password=secret test
  - Read them in tests via System.getProperty("api.email") and System.getProperty("api.password").

- CI/CD secret management: use your CI provider's secret store and inject as environment variables at runtime.

## Test behavior

- Tests use relaxed HTTPS validation to tolerate self-signed certificates (can be removed in production).
- The login test asserts HTTP 200 and will check for a returned JSON `token` field if present.
- Tests print response body to stdout for debugging when assertions fail.

## Troubleshooting

- Dependency resolution failures: run `mvn -U test` to force update of dependencies.
- SSL errors: verify certificate or disable relaxed HTTPS validation only for local testing.
- If tests fail with unexpected status codes, inspect the printed response body for error details.

## Next steps

- Parameterize tests to use environment variables or system properties for credentials.
- Add negative tests (invalid credentials, missing fields) and other endpoints (registration, token refresh).
- Extract common setup into a base test or RequestBuilder utility.
