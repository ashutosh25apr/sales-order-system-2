I have modified the `build-all.sh` script to make it more robust against directory changes. Each service build is now executed in a subshell, which prevents "directory not found" errors if a previous command fails.

This should allow the script to execute correctly locally and in GitHub Actions.

To apply the fix, you need to:

1.  Commit the changes I've made to your repository.
2.  Push the changes to the `main` branch.

This will trigger the workflow with the corrected script.

If you want me to commit and push the changes for you, please let me know.
