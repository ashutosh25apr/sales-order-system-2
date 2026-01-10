Thank you for the logs. This snippet confirms that the Docker-in-Docker service itself is starting up successfully, which is good.

However, I still need the **full logs** from the `Build Order Service` step, including all output from the `:test` task where the `ConnectException` occurs. I'm specifically looking for the log line that starts with:

`ERROR Setting spring.data.mongodb.uri to: ...`

This log line should appear in the test output, and it's critical for understanding what connection string Testcontainers is actually providing to your Spring Boot application during the tests.

Please provide the *full output* from the "Build Order Service" step, which includes all the Gradle task executions and the test results.