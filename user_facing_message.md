I have created separate GitHub Actions workflows for each service, which will be triggered only when changes occur in their respective directories. This strategy offers faster builds, better isolation, and clearer feedback for each service.

I have also reverted some of the previous temporary fixes to the `order-service/gradlew` script and removed the `build-all.sh` script, as these are no longer necessary with the new workflow structure.

To apply these changes and initiate the new workflow, you need to:

1.  Commit the changes I've made to your repository.
2.  Push the changes to the `main` branch.

This will trigger the individual workflows based on the changed files. You can monitor their progress in the "Actions" tab of your GitHub repository.

If you want me to commit and push the changes for you, please let me know.