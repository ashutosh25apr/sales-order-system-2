Okay, good news! The compilation errors are gone. This means all the code and dependency updates for Java 21 are working correctly.

However, we are back to the `java.net.ConnectException` during test execution. This indicates that the Spring Boot application, when running its tests, is unable to establish a connection with the Testcontainers-managed MongoDB instance.

I added a logging statement (`LOGGER.info("Setting spring.data.mongodb.uri to: {}", mongoUri);`) to `ApiOrderIntegrationTest.java` specifically to help diagnose this.

To proceed, I need the **full logs** from the latest GitHub Actions run for the `Order Service Build` workflow. I will be looking for the line that starts with "Setting spring.data.mongodb.uri to:". This will tell us the exact connection URI that Testcontainers is providing to your Spring Boot application during tests.

Please go to the "Actions" tab in your GitHub repository, find the latest run for the `Order Service Build` workflow, and paste the *full output* here. Once I have those logs, I can analyze the connection string and propose the next steps to resolve the `ConnectException`.
