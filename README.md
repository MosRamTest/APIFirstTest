# APIFirstTest

Maven project for API testing using RestAssured and JUnit 5.

## Project overview

This repository contains automated API tests implemented with RestAssured and JUnit 5. The included LoginTest demonstrates how to perform a POST login request against the project's API endpoint. Credentials are intentionally excluded from source — see the Configuration section for secure ways to provide secrets at runtime.

## Project structure (relevant files)

- pom.xml — Maven configuration (RestAssured, JUnit, Hamcrest)
- src/test/java/org/example/LoginTest.java — Login test example (reads credentials from env vars or system properties)
- src/test/java/... — other test packages and helpers

## Prerequisites

- Java 21 (JDK)
- Maven 3.8+
- Network access to the target API

## How the login test works

- Endpoint used in the tests: https://www.ndosiautomation.co.za/API/login
- The test sends a JSON body with email and password (the test reads values from environment variables or Maven properties; it does not contain credentials in source control).
- The test uses relaxed HTTPS validation (RestAssured.useRelaxedHTTPSValidation()) to avoid TLS issues in local/test environments — remove or change in production as needed.
- The test asserts a successful HTTP response (adjust assertions as required by your API) and prints the response body for debugging.

## Securely supplying credentials (do NOT store real credentials in source)

Recommended approaches:

1) Environment variables (recommended)

- Set environment variables before running tests:
  - API_EMAIL — the login email
  - API_PASSWORD — the login password

Examples:
- PowerShell:
  $env:API_EMAIL = "you@example.com"; $env:API_PASSWORD = "yourPassword"; mvn test

- Windows CMD:
  set API_EMAIL=you@example.com && set API_PASSWORD=yourPassword && mvn test

2) Maven system properties (ephemeral)

- Pass credentials on the mvn command line (they will not be stored in the repository):
  mvn -Dapi.email=you@example.com -Dapi.password=yourPassword test

- In tests read via: System.getProperty("api.email") and System.getProperty("api.password").

3) CI/CD secrets (best for automated pipelines)

- Use your CI provider's secret store (GitHub Actions secrets, GitLab CI variables, Jenkins credentials, etc.) and inject them into the build as environment variables.

Important: treat secrets securely. Avoid echoing them in logs and do not commit them to version control.

## Run tests

From the project root run:

mvn test

To run only the login test method:

mvn -Dtest=org.example.LoginTest#loginWithEmailAndPassword test

If you pass credentials via Maven properties and want to run only the login method, combine both options:

mvn -Dapi.email=you@example.com -Dapi.password=yourPassword -Dtest=org.example.LoginTest#loginWithEmailAndPassword test

## Test behavior and tips

- The login test will be skipped if credentials are not provided (JUnit assumptions can be used in the test to skip when credentials are missing).
- If your environment blocks outgoing traffic, make sure you have network access to the target host or run tests against a mocked/staging endpoint.
- If your API returns different success status codes or additional response fields (for example `token`, `userId`, or `role`), update assertions in the test accordingly.

## Troubleshooting

- Dependency resolution failures: run `mvn -U test` to force-update dependencies.
- SSL/TLS errors: check certificates or remove relaxed TLS in tests only after validating certs.
- Unexpected response codes: inspect printed response body to see API error messages.

## Next steps

- Parameterize and centralize configuration (baseURI, timeouts, common headers) in a base test class.
- Add negative tests for invalid credentials and missing fields.
- Integrate with CI and use secret management to inject credentials securely.

If you want, I can:
- Update README with CI-specific examples (GitHub Actions, GitLab CI).
- Refactor tests to centralize configuration and remove relaxed TLS for production runs.
- Add an example .env file template (excluded from commits) and .gitignore entries for local secret files.
