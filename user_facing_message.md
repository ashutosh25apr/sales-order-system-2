I have modified the `build-all.sh` script to correctly set the `JAVA_HOME` and `PATH` environment variables for each service, and I have updated the GitHub Actions workflow to remove the job-level `env` variables and inline the build commands directly into the workflow.

This should fix the "Unrecognized named-value: 'steps'" error and the Java version mismatch during the `order-service` build.

To apply the fix, you need to:

1.  Commit the changes I've made to your repository.
2.  Push the changes to the `main` branch.

This will trigger the workflow with the corrected environment.

If you want me to commit and push the changes for you, please let me know.
