I have modified the GitHub Actions workflow to force Gradle to not use a daemon by adding `--no-daemon` to the build command for the `order-service`. This should prevent the Gradle daemon from using an incompatible Java version and fix the "Unsupported class file major version 61" error.

To apply the fix, you need to:

1.  Commit the changes I've made to your repository.
2.  Push the changes to the `main` branch.

This will trigger the workflow with the corrected environment.

If you want me to commit and push the changes for you, please let me know.