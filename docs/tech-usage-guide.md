# Good Practices for Various Technologies
## Slack
- Make use of threads. It keeps things clean and streamlined so you don't have to scroll forever to find the discussion you're looking for.
- Keep all discussion in public channels unless it is personal discussion unrelated to the project. This promotes visibility and improves situational awareness of the entire team.
- Make reasonable use of slack channels. If there is a topic that comes up frequently, it may be useful to create a new channel for discussion about it.

## Git / Repository Management
### Keep Everything in the Repository
If at all possible, it is good to keep everything within the repository. While large organizations may have multiple repositories, we are only working on a small project, so a single repository is valuable due to the colocation of all information and resources. Links to external resources prevent git from tracking changes and increases the likelihood of items being lost.

### Working with Branches
When working on a part of a larger project, it can be very helpful to keep the `main` branch as clean as possible. To ensure that `main` contains only good, vetted code, it can be protected in a few different ways:
- Prevent commits directly to `main`.
- Perform all work in branches.
- Requre approvals after code reviews before merging PRs into `main`.

### Code Reviews and Approvals  
Reviewing code is essential to improving the quality of code merged into `main`. While it does take more time for a team to effectively review code before merging, it is worth it in the long run as less time will be spent on general code cleanup and locating and fixing bugs. Some things to keep in mind when requesting a code review or reviewing someone else's code:
- Reviews are an opportunity to improve the quality of work being completed. If a genuine effort is not put forth during the review, significantly fewer improvements will be made. Skimming through code and submitting an approval because someone wants to merge a feature quickly is not effective review technique.
- Constructive feedback is a must. Simply pointing out things that you don't like does not help someone write better code. Try to include recommendations or possible solutions for fixes, restructuring, etc. when writing review comments. 
- It's ok to request changes. Requesting changes does not mean the author is a terrible programmer, so don't let it hurt your ego. Everyone makes mistakes and raising genuine concerns through review comments only helps the team's code. On the other hand, if you disagree with something due to a personal preference that does not effect the clarity, maintainability, or functionality of the code (i.e. variable names), you should leave it up to the author by leaving a comment and approving the merge.

### Squash Merging
When work is complete, reviewed, and ready to merge, *squash merging* combines all commits within the branch into one big commit to `main`. This method retains fine-grained commit logs within individual branches, while providing a decluttered, big-picture view of what is happening with the overall project in `main`. As an additional incentive, viewing a commit diff for a commit to `main` reveals all code pertaining to the feat/fix/docs within one view.

### Conventional Commits and Branch Naming
The [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0-beta.2/) specification goes hand-in-hand with squash merging and provides a clear way of identifying what work is being merged into `main`. While all of the intricacies of Conventional Commits may not be necessary for our purposes, a few ideas can be very helpful. The name Conventional *Commits* may be a bit misleading in our use case, as we would not use the structure to label our commits. Rather, we would use the Conventional Commit format for titling out PRs, which become the squashed commit name within `main` after performing a squash merge. Branch names can mirror the Conventional Commit titles to designate clear connections between branches and the commits that show up in `main`.

## Examples

Example 1:

1. A new document, `tech-usage-guide.md` is being created to define good practices for project technologies  
2. A new branch is created called `docs/adhoc-tech-usage-guide`.
3. The document is created and modified. Branch `docs/adhoc-tech-usage-guide` receives regular commits with messages describing changes as they occur.
4. The document is completed and a PR is created. The PR is titled `docs(adhoc): Tech Usage Guide`.
5. The branch is squash merged into `main`. Viewing the git log reveals one commit, `docs(adhoc): Tech Usage Guide`.

Example 2:

1. A new feature is being developed to allow files to be deleted. It is story #7.
2. A new branch is created called `feat/7`.
3. The feature is developed and the `feat/7` branch recieves regular commits with messages describing changes as they occur.
4. The feature is completed and a PR is created. The PR is titled `feat(7): Allow File Deletion`.
5. The branch is squash merged into `main`. Viewing the git log reveals one commit, `feat(7): Allow File Deletion`.

Example 3:

1. A bug is found within the file deletion feature. It is tracked as bug #23
2. A new branch is created called `fix/23`.
3. The bugfix is implemented and the `fix/23` branch receives regular commits with messages describing changes as they occur.
4. The bugfix is completed and a PR is created. The PR is titled `fix(23): Files now delete instead of go to trash`.
5. The branch is squash merged into `main`. Viewing the git log reveals one commit, `fix(23): Files now delete instead of go to trash`