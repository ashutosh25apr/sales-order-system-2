I have modified the `build-all.sh` script to make it more robust. It will now exit immediately if any command fails, which will prevent the script from continuing in an inconsistent state and producing misleading errors.

This should help us to identify the root cause of the build failure more easily.

To apply the fix, you need to:

1.  Commit the changes I've made to your repository.
2.  Push the changes to the `main` branch.

This will trigger the workflow with the corrected script.

If you want me to commit and push the changes for you, please let me know.
