The local build for `order-service` is unfortunately still failing with Testcontainers unable to connect to the Docker daemon, despite all recent fixes. This issue appears to be a persistent environmental problem specific to your local machine's Docker setup (Colima on ARM64) when running Testcontainers, which is difficult to debug remotely.

However, I have now committed and pushed all changes that should resolve the `WeakKeyException` and ensure `order-service` is fully migrated to Java 21 and compatible with Spring Boot 3 in a standard environment.

**Next Steps:**
We need to rely on the GitHub Actions environment to confirm the success of this migration.

Please monitor the "Actions" tab of your GitHub repository. The dedicated workflow for the `order-service` should have been triggered by my last push.

**Please provide the logs from the GitHub Actions run for the `Order Service Build` workflow that was triggered by the very last push (the one fixing the `WeakKeyException` in test properties).**

This will confirm if the `order-service` migration to Java 21 is finally complete and working as expected in a reliable CI environment.