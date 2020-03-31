#Contributing

First and foremost, we appreciate your interest in this project. This document contains essential information, should you want to contribute.

#Development discussion

For bugs, new features or improvements, open a new issue.

#Which Branch?

Pull requests should always be done against the master branch.

#Committing to git

Each commit MUST have a proper message describing the work that has been done. This is called Semantic Commit Messages.

Here's what a commit message should look like:
```
feat(Occurrences): implement API client to fetch occurrence data
^--^ ^---------^   ^-------------------------------------------^
|    |             |
|    |             +-> Description of the work in the present tense.
|    |
|    +---------------> (Optional) Scope of the work.
|
+--------------------> Type: chore, docs, feat, fix, hack, refactor, style, or test.
```
#Branching strategy
We will be using the branch-per-issue workflow.

This means, that for each open issue, we'll create a corresponding git branch.

For instance, issue #123 should have a corresponding feature/SUNLINEAPP-123-ShortTaskDescription branch, which MUST branch off the latest code in master.