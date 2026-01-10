The local build of `order-service` is still failing with the `NotFoundException` from Testcontainers, indicating it cannot find or start the `mongo:6.0.4` Docker image, despite explicitly pulling the `amd64` image and configuring Testcontainers.

This issue appears to be a deep-seated incompatibility or misconfiguration specific to your local Docker environment (Colima on ARM64) when trying to run `amd64` images with Testcontainers. I have exhausted the practical options for troubleshooting this locally without making potentially disruptive changes to your local Docker setup, which is beyond my capabilities as an agent.

Therefore, the only way forward is to rely on the GitHub Actions environment. I have already applied all the necessary changes to migrate the `order-service` to Java 21, including:
*   Upgrading Gradle to 8.7.
*   Updating `build.gradle` for Java 21, Spring Boot 3.2.3, and compatible dependencies.
*   Refactoring `WebSecurityConfig.java` for Spring Security 6.
*   Replacing Springfox with SpringDoc OpenAPI.
*   Updating `ApiOrderIntegrationTest.java` for Testcontainers with explicit platform forcing and timeouts.
*   Updating `jwt.secret` to a stronger key.

I have already committed and pushed the latest changes to the `main` branch.

**Next Steps:**
Please monitor the "Actions" tab of your GitHub repository. The dedicated workflow for the `order-service` should now be triggered. This build in the GitHub Actions environment (which is `linux/amd64`) should give us the definitive answer on whether the `order-service` can build and test successfully with Java 21.

Please provide the logs from the GitHub Actions run for the `Order Service Build` workflow.
