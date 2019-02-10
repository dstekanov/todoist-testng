POC for TestNG & Todoist (https://www.todoist.com/)

###Git workflow:

We are using Feature Branch Workflow _(https://www.atlassian.com/git/tutorials/comparing-workflows/feature-branch-workflow)_

1. Before pushing, do rebase onto **master** _(and resolve conflicts if any)_ 
2. Use a separate branch for each feature or issue you work on
2. On this branch, **commit** changes in the usual fashion, building up the feature with as many commits as necessary
3. `TODO:` determine feature-branch naming
4. When ready, **push** feature branch to remote
5. Create a **pull request** in Bitbucket
6. When your pull request is approved and conflict-free, **merge it to the master**

####"Commit Changes" dialog

**"Before commit"** section:

1. Select "Reformat code" checkbox to perform code formatting according to the Project Code Style settings
2. Select "Optimize imports" checkbox to remove redundant import statements
3. Select "Perform code analysis" checkbox to run code inspection on the files you are about to commit
4. Select "Check TODO" checkbox to review the TODO items
5. DO NOT select:
- "Rearrange code"
- "Cleanup"
- "Update copyright"

---

UI test scenarios:

-
-