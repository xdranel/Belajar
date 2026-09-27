# Git Cheat Sheet

> A practical, easy-to-read Git reference for beginners and intermediate developers.
>
> **Core idea:** Git tracks changes to files. Most daily Git work is about moving changes between **Working Directory → Staging Area → Commit History → Remote Repository**.

---

## Table of Contents

1. [Git Mental Model](#1-git-mental-model)
2. [Install & First Setup](#2-install--first-setup)
3. [Create or Get a Repository](#3-create-or-get-a-repository)
4. [The Three Main Areas](#4-the-three-main-areas)
5. [Everyday Workflow](#5-everyday-workflow)
6. [Checking Changes](#6-checking-changes)
7. [Adding Changes](#7-adding-changes)
8. [Committing](#8-committing)
9. [Viewing History](#9-viewing-history)
10. [Branches](#10-branches)
11. [Merging](#11-merging)
12. [Rebase](#12-rebase)
13. [Remote Repositories](#13-remote-repositories)
14. [Push, Pull & Fetch](#14-push-pull--fetch)
15. [GitHub Workflow](#15-github-workflow)
16. [Undoing Changes](#16-undoing-changes)
17. [Reset](#17-reset)
18. [Revert](#18-revert)
19. [Stash](#19-stash)
20. [Tags](#20-tags)
21. [.gitignore](#21-gitignore)
22. [Git Diff](#22-git-diff)
23. [Cherry-Pick](#23-cherry-pick)
24. [Merge Conflicts](#24-merge-conflicts)
25. [Remote Branches](#25-remote-branches)
26. [Tracking Branches](#26-tracking-branches)
27. [Useful Log & Graph Commands](#27-useful-log--graph-commands)
28. [Searching Git History](#28-searching-git-history)
29. [Git Aliases](#29-git-aliases)
30. [Useful Configuration](#30-useful-configuration)
31. [Commit Message Conventions](#31-commit-message-conventions)
32. [Common Workflows](#32-common-workflows)
33. [Common Mistakes](#33-common-mistakes)
34. [Dangerous Commands](#34-dangerous-commands)
35. [Recovery](#35-recovery)
36. [Intermediate Commands](#36-intermediate-commands)
37. [Quick Reference Tables](#37-quick-reference-tables)
38. [One-Page Daily Cheat Sheet](#38-one-page-daily-cheat-sheet)

---

# 1. Git Mental Model

Git is a **distributed version control system**.

Think of a project as having four important places:

```text
                 git add
Working        ----------->       Staging Area
Directory                         (Index)
   |                                  |
   |                                  | git commit
   |                                  v
   |                            Local Repository
   |                            (Commit History)
   |                                  |
   |                                  | git push
   |                                  v
   +----------------------------> Remote Repository
                                      (GitHub/GitLab/etc.)
```

### Working Directory

The actual files you are editing.

```text
index.html
style.css
script.js
```

### Staging Area

Files/changes selected for the next commit.

```bash
git add index.html
```

### Local Repository

Your local Git history.

```bash
git commit -m "Add homepage"
```

### Remote Repository

A repository hosted somewhere else, such as GitHub.

```bash
git push
```

---

# 2. Install & First Setup

## Check Git version

```bash
git --version
```

## Configure your name

```bash
git config --global user.name "Your Name"
```

## Configure your email

```bash
git config --global user.email "you@example.com"
```

## Check configuration

```bash
git config --list
```

## Check one value

```bash
git config user.name
git config user.email
```

## Set default branch name

```bash
git config --global init.defaultBranch main
```

## Set VS Code as editor

```bash
git config --global core.editor "code --wait"
```

---

# 3. Create or Get a Repository

## Create a new Git repository

Inside your project:

```bash
git init
```

This creates:

```text
.git/
```

`.git` contains Git's internal repository data.

> **Do not delete or modify `.git` manually unless you know exactly what you are doing.**

---

## Clone an existing repository

```bash
git clone <repository-url>
```

Example:

```bash
git clone https://github.com/user/project.git
```

Clone and choose a directory name:

```bash
git clone <repository-url> my-project
```

---

## Check repository status

```bash
git status
```

This is one of the most important Git commands.

**When confused, run:**

```bash
git status
```

---

# 4. The Three Main Areas

The three core Git areas are:

```text
Working Directory
       |
       | git add
       v
Staging Area
       |
       | git commit
       v
Repository
```

### Example

You edit:

```text
app.js
```

Git sees:

```text
Modified: app.js
```

Then:

```bash
git add app.js
```

Now:

```text
Changes to be committed:
    modified: app.js
```

Then:

```bash
git commit -m "Fix app logic"
```

Now the change is stored in Git history.

---

# 5. Everyday Workflow

The basic workflow:

```bash
git status
git add .
git commit -m "Describe what changed"
git push
```

More carefully:

```bash
# 1. See what changed
git status

# 2. Inspect changes
git diff

# 3. Stage selected changes
git add file.txt

# 4. Check staged changes
git diff --staged

# 5. Commit
git commit -m "Add feature"

# 6. Send commits to remote
git push
```

For a typical feature:

```bash
git switch -c feature/login

# edit files

git add .
git commit -m "Add login feature"

git push -u origin feature/login
```

---

# 6. Checking Changes

## Status

```bash
git status
```

Short version:

```bash
git status -s
```

Example:

```text
 M app.js
A  login.html
?? notes.txt
```

Meaning:

| Symbol | Meaning |
|---|---|
| `M` | Modified |
| `A` | Added |
| `D` | Deleted |
| `??` | Untracked |

---

## See unstaged changes

```bash
git diff
```

This compares:

```text
Working Directory
        vs
Staging Area
```

---

## See staged changes

```bash
git diff --staged
```

or:

```bash
git diff --cached
```

This compares:

```text
Staging Area
        vs
Last Commit
```

---

## Compare two commits

```bash
git diff <commit1> <commit2>
```

Example:

```bash
git diff abc123 def456
```

---

# 7. Adding Changes

## Add one file

```bash
git add app.js
```

## Add multiple files

```bash
git add app.js style.css
```

## Add a directory

```bash
git add src/
```

## Add everything

```bash
git add .
```

### `git add .` vs `git add -A`

```bash
git add .
```

Stages changes under the current directory.

```bash
git add -A
```

Stages all changes in the repository.

For most normal projects:

```bash
git add .
```

is convenient.

---

## Interactively choose changes

```bash
git add -p
```

This lets you stage individual chunks instead of an entire file.

Very useful when one file contains unrelated changes.

---

# 8. Committing

## Basic commit

```bash
git commit -m "Add login page"
```

A commit is a snapshot of staged changes.

---

## Commit with a detailed message

```bash
git commit
```

Git opens your configured editor.

---

## Add and commit tracked files

```bash
git commit -am "Fix login bug"
```

Important:

`-a` stages modifications/deletions to **already tracked files**.

It does **not** stage new untracked files.

So this:

```bash
git commit -am "Add file"
```

will NOT include:

```text
new-file.js
```

if it was untracked.

---

## Amend the latest commit

Forgot something?

```bash
git add forgotten-file.js
git commit --amend
```

Change only the message:

```bash
git commit --amend -m "Better commit message"
```

> Avoid amending commits that have already been shared with other people unless you understand the consequences.

---

# 9. Viewing History

## Basic log

```bash
git log
```

## Compact log

```bash
git log --oneline
```

Example:

```text
a1b2c3d Add login
e4f5g6h Fix navbar
i7j8k9l Initial commit
```

## Show branches as a graph

```bash
git log --oneline --graph --decorate --all
```

This is one of the best history commands.

---

## Last 5 commits

```bash
git log -5
```

or:

```bash
git log -5 --oneline
```

## Show a commit

```bash
git show <commit>
```

Example:

```bash
git show a1b2c3d
```

---

## Show only commit statistics

```bash
git show --stat <commit>
```

---

## Search commit messages

```bash
git log --grep="login"
```

Case-insensitive:

```bash
git log --grep="login" -i
```

---

# 10. Branches

A branch is essentially a movable pointer to commits.

Think:

```text
main
 |
 A---B---C
          \
           D---E   feature/login
```

`main` and `feature/login` can develop independently.

---

## List branches

```bash
git branch
```

All local + remote branches:

```bash
git branch -a
```

Remote branches:

```bash
git branch -r
```

---

## Create a branch

```bash
git branch feature/login
```

This creates the branch but does not switch to it.

---

## Create and switch

Modern Git:

```bash
git switch -c feature/login
```

Older/common command:

```bash
git checkout -b feature/login
```

Recommended for beginners:

```bash
git switch -c feature/login
```

---

## Switch branches

```bash
git switch main
```

or:

```bash
git switch feature/login
```

---

## Delete a local branch

Safe delete:

```bash
git branch -d feature/login
```

Force delete:

```bash
git branch -D feature/login
```

Use `-D` carefully.

---

## Rename current branch

```bash
git branch -m new-name
```

Rename another local branch:

```bash
git branch -m old-name new-name
```

---

# 11. Merging

Suppose:

```text
main:
A---B---C

feature:
     \
      D---E
```

You want feature changes in `main`.

```bash
git switch main
git merge feature
```

Possible result:

```text
A---B---C---D---E
```

This is a **fast-forward merge** if `main` has not moved since the feature branch was created.

---

## Merge with a merge commit

```bash
git merge --no-ff feature/login
```

This preserves an explicit merge commit.

---

## Abort a merge

If there are conflicts and you want to cancel:

```bash
git merge --abort
```

---

# 12. Rebase

Rebase moves/replays commits onto another base.

Before:

```text
A---B---C        main
     \
      D---E      feature
```

After rebasing feature onto main:

```text
A---B---C---D'---E'    feature
```

Commands:

```bash
git switch feature
git rebase main
```

---

## Why rebase?

It can produce a cleaner linear history.

Instead of:

```text
A---B---C
     \   \
      D---E
           \
            M
```

you can have:

```text
A---B---C---D'---E'
```

---

## Continue after conflict

```bash
git add <resolved-files>
git rebase --continue
```

## Abort rebase

```bash
git rebase --abort
```

---

## Important rebase rule

Rebase rewrites commit history.

Avoid rebasing commits that other people are already depending on unless your team explicitly agrees.

### Simple rule

```text
Private/local commits → rebase is usually fine.

Shared/public commits → be careful.
```

---

# 13. Remote Repositories

A remote is another copy/reference to a repository.

Usually:

```text
origin
```

means the default remote repository.

---

## Show remotes

```bash
git remote
```

More details:

```bash
git remote -v
```

Example:

```text
origin  https://github.com/user/project.git (fetch)
origin  https://github.com/user/project.git (push)
```

---

## Add a remote

```bash
git remote add origin <repository-url>
```

Example:

```bash
git remote add origin https://github.com/user/project.git
```

---

## Change remote URL

```bash
git remote set-url origin <new-url>
```

---

## Remove remote

```bash
git remote remove origin
```

---

# 14. Push, Pull & Fetch

These three commands are commonly confused.

## `git fetch`

Downloads remote information.

```bash
git fetch
```

It updates your knowledge of the remote without changing your current files.

Think:

```text
Remote
  |
  | fetch
  v
Remote-tracking refs
```

---

## `git pull`

Usually:

```bash
git pull
```

is approximately:

```bash
git fetch
git merge
```

Depending on configuration, pull can also use rebase.

So:

```text
pull = fetch + integrate
```

---

## `git push`

Uploads your local commits.

```bash
git push
```

First push of a new branch:

```bash
git push -u origin feature/login
```

After that:

```bash
git push
```

usually works.

---

## Push current branch and set upstream

```bash
git push -u origin HEAD
```

Useful because Git automatically uses the current branch name.

---

## Delete a remote branch

```bash
git push origin --delete feature/login
```

---

# 15. GitHub Workflow

Typical GitHub workflow:

```text
Clone
  ↓
Create branch
  ↓
Edit
  ↓
git add
  ↓
git commit
  ↓
git push
  ↓
Pull Request
  ↓
Code review
  ↓
Merge
```

Example:

```bash
git clone <repo-url>
cd project

git switch -c feature/login

# edit files

git status
git diff

git add .
git commit -m "Add login feature"

git push -u origin feature/login
```

Then open a Pull Request on GitHub.

---

# 16. Undoing Changes

Git has several different kinds of "undo."

The correct command depends on **where the change currently exists**.

---

## Undo unstaged changes

You changed a file but have not staged it.

Modern:

```bash
git restore app.js
```

This restores the file to the staged version.

If nothing is staged, that means the latest committed version.

---

## Undo all unstaged changes

```bash
git restore .
```

**Danger:** uncommitted changes can be permanently lost.

---

## Unstage a file

You ran:

```bash
git add app.js
```

but want to remove it from staging:

```bash
git restore --staged app.js
```

The file's changes remain in your working directory.

---

## Unstage everything

```bash
git restore --staged .
```

---

# 17. Reset

`git reset` moves `HEAD` and/or changes what is staged.

There are three major modes:

```text
--soft
--mixed
--hard
```

---

## Soft reset

```bash
git reset --soft HEAD~1
```

Moves HEAD back one commit.

Changes remain staged.

```text
Commit A---Commit B
           ^
          HEAD

after:

Commit A
   ^
  HEAD

B's changes → staged
```

Useful when you want to redo a commit.

---

## Mixed reset

Default:

```bash
git reset HEAD~1
```

or:

```bash
git reset --mixed HEAD~1
```

Moves HEAD back and unstages changes.

The file changes remain in the working directory.

---

## Hard reset

```bash
git reset --hard HEAD~1
```

Moves HEAD back and discards changes.

**Dangerous.**

Possible mental model:

```text
Commit history  ← moved back
Staging         ← reset
Working files   ← reset
```

Do not use this casually.

---

## Reset to a specific commit

```bash
git reset --hard <commit>
```

---

# 18. Revert

`git revert` creates a **new commit that reverses an old commit**.

Example:

```text
A---B---C
```

Run:

```bash
git revert C
```

Result:

```text
A---B---C---C'
```

`C'` reverses the changes introduced by `C`.

### Why use revert?

It is usually safer for shared history because it does not rewrite existing commits.

### Simple rule

```text
Shared/public history → revert

Private/local history → reset/rebase can be appropriate
```

---

# 19. Stash

Stash temporarily stores uncommitted changes.

Useful when:

```text
You are working on feature A
        ↓
You suddenly need to switch to feature B
        ↓
But your current changes aren't ready to commit
```

---

## Save changes

```bash
git stash
```

Better:

```bash
git stash push -m "Work in progress login"
```

---

## Include untracked files

```bash
git stash -u
```

---

## List stashes

```bash
git stash list
```

Example:

```text
stash@{0}: On feature/login: Work in progress
stash@{1}: On main: Temporary changes
```

---

## Apply latest stash

```bash
git stash apply
```

This keeps the stash in the stash list.

---

## Apply a specific stash

```bash
git stash apply stash@{1}
```

---

## Apply and remove stash

```bash
git stash pop
```

---

## Delete a stash

```bash
git stash drop stash@{0}
```

---

## Delete all stashes

```bash
git stash clear
```

Be careful: this removes all stashes.

---

# 20. Tags

Tags give names to specific commits.

Commonly used for releases:

```text
v1.0.0
v1.1.0
v2.0.0
```

---

## Create lightweight tag

```bash
git tag v1.0.0
```

---

## Create annotated tag

```bash
git tag -a v1.0.0 -m "Version 1.0.0"
```

Annotated tags are generally preferable for releases.

---

## List tags

```bash
git tag
```

---

## Show tag

```bash
git show v1.0.0
```

---

## Push one tag

```bash
git push origin v1.0.0
```

## Push all tags

```bash
git push origin --tags
```

---

## Delete local tag

```bash
git tag -d v1.0.0
```

## Delete remote tag

```bash
git push origin --delete v1.0.0
```

---

# 21. `.gitignore`

`.gitignore` tells Git which files should not be tracked.

Example:

```gitignore
node_modules/
.env
*.log
dist/
build/
.DS_Store
```

Typical Node.js `.gitignore`:

```gitignore
node_modules/
.env
dist/
coverage/
*.log
```

Typical Java/Spring Boot:

```gitignore
target/
*.class
.idea/
*.iml
.vscode/
.env
```

---

## Ignore a specific file

```gitignore
secret.txt
```

## Ignore all `.log` files

```gitignore
*.log
```

## Ignore a directory

```gitignore
node_modules/
```

## Ignore `.env` files

```gitignore
.env
.env.local
```

---

## Important `.gitignore` rule

If a file is **already tracked**, adding it to `.gitignore` does not automatically untrack it.

Use:

```bash
git rm --cached .env
```

Then commit:

```bash
git commit -m "Stop tracking env file"
```

---

# 22. Git Diff

## Working directory vs staging

```bash
git diff
```

## Staging vs last commit

```bash
git diff --staged
```

## Two commits

```bash
git diff <commit1> <commit2>
```

## Two branches

```bash
git diff main..feature/login
```

## File-specific diff

```bash
git diff -- app.js
```

---

# 23. Cherry-Pick

Cherry-pick copies a specific commit onto your current branch.

Suppose:

```text
main:
A---B---C

feature:
     \
      D---E
```

You want only `D` on main.

```bash
git switch main
git cherry-pick <D>
```

Result:

```text
A---B---C---D'
```

Useful when:

- You need one bug fix from another branch.
- You do not want to merge the entire branch.
- You need to move a small specific change.

---

## Continue cherry-pick after conflict

```bash
git add <resolved-files>
git cherry-pick --continue
```

## Abort

```bash
git cherry-pick --abort
```

---

# 24. Merge Conflicts

A conflict happens when Git cannot automatically decide which changes to keep.

Example:

```text
<<<<<<< HEAD
console.log("Hello");
=======
console.log("Hi");
>>>>>>> feature
```

You manually edit it to the desired result:

```javascript
console.log("Hello");
```

Then:

```bash
git add app.js
git commit
```

For a rebase:

```bash
git add app.js
git rebase --continue
```

---

## Conflict workflow

```text
Conflict
   ↓
Open conflicting file
   ↓
Choose/fix content
   ↓
Remove <<<<<<< ======= >>>>>>>
   ↓
git add <file>
   ↓
continue merge/rebase/cherry-pick
```

### Abort merge

```bash
git merge --abort
```

### Abort rebase

```bash
git rebase --abort
```

### Abort cherry-pick

```bash
git cherry-pick --abort
```

---

# 25. Remote Branches

Fetch remote branches:

```bash
git fetch
```

See them:

```bash
git branch -r
```

Example:

```text
origin/main
origin/feature/login
```

---

## Create local branch from remote

```bash
git switch -c feature/login origin/feature/login
```

Modern shortcut:

```bash
git switch feature/login
```

If Git can uniquely match a remote branch, it may automatically create a tracking branch.

---

# 26. Tracking Branches

A local branch can track a remote branch.

Example:

```text
local:  main
           ↓
remote: origin/main
```

Then:

```bash
git pull
git push
```

can know where to pull from and push to.

---

## Set upstream

```bash
git push -u origin main
```

The `-u` means:

```text
--set-upstream
```

After this:

```bash
git push
git pull
```

usually work without specifying the remote/branch.

---

## See tracking information

```bash
git branch -vv
```

---

# 27. Useful Log & Graph Commands

## Best general history command

```bash
git log --oneline --graph --decorate --all
```

## More readable log

```bash
git log --oneline --graph --decorate --all --color
```

## Show recent commits

```bash
git log -10 --oneline
```

## Show files changed in commits

```bash
git log --stat
```

## Show patch for commits

```bash
git log -p
```

## History of a specific file

```bash
git log -- app.js
```

## Follow a file through renames

```bash
git log --follow -- app.js
```

---

# 28. Searching Git History

## Find a commit by message

```bash
git log --grep="authentication"
```

## Find who changed a line

```bash
git blame app.js
```

Example:

```bash
git blame -L 10,30 app.js
```

This shows which commit/person last changed each line.

---

## Search code in history

```bash
git log -S "someFunction"
```

Find commits where the number of occurrences of a string changed.

---

## Regex search

```bash
git log -G "function.*login"
```

---

# 29. Git Aliases

Aliases create shortcuts.

Example:

```bash
git config --global alias.st status
```

Now:

```bash
git st
```

means:

```bash
git status
```

---

## Useful aliases

```bash
git config --global alias.st "status -sb"
git config --global alias.co checkout
git config --global alias.br branch
git config --global alias.cm "commit -m"
git config --global alias.lg "log --oneline --graph --decorate --all"
```

Then:

```bash
git st
git br
git lg
```

---

# 30. Useful Configuration

## List config

```bash
git config --list
```

## Edit global config

```bash
git config --global --edit
```

## Set default pull behavior to rebase

```bash
git config --global pull.rebase true
```

Only do this if it matches your team's workflow.

---

## Enable useful colors

```bash
git config --global color.ui auto
```

---

## Set default branch

```bash
git config --global init.defaultBranch main
```

---

# 31. Commit Message Conventions

Good commit:

```text
Add user authentication
```

Bad commit:

```text
stuff
```

Bad:

```text
changes
```

Bad:

```text
final final 2
```

---

## Common style

```text
Add login page
Fix null pointer in user service
Update README
Remove deprecated endpoint
Refactor authentication service
```

A commit should ideally answer:

> **What did this commit change?**

---

## Conventional Commits

A popular convention:

```text
feat: add login page
fix: prevent duplicate orders
docs: update README
refactor: simplify authentication service
test: add user service tests
chore: update dependencies
```

Common types:

| Type | Purpose |
|---|---|
| `feat` | New feature |
| `fix` | Bug fix |
| `docs` | Documentation |
| `refactor` | Code restructuring |
| `test` | Tests |
| `chore` | Maintenance |
| `perf` | Performance |
| `build` | Build/dependency changes |
| `ci` | CI/CD changes |

---

# 32. Common Workflows

## Workflow A — Simple solo project

```bash
git status
git add .
git commit -m "Add feature"
git push
```

---

## Workflow B — Start a new feature

```bash
git switch main
git pull

git switch -c feature/login

# work...

git add .
git commit -m "Add login feature"

git push -u origin feature/login
```

Then create a Pull Request.

---

## Workflow C — Update your feature branch

```bash
git switch main
git pull

git switch feature/login
git merge main
```

Or, if your team uses rebase:

```bash
git switch feature/login
git rebase main
```

---

## Workflow D — Quick bug fix

```bash
git switch main
git pull

git switch -c fix/navbar-overflow

# fix...

git add .
git commit -m "Fix navbar overflow"

git push -u origin fix/navbar-overflow
```

---

## Workflow E — Temporarily switch tasks

```bash
git stash push -m "WIP login"
git switch main

# do urgent work

git switch feature/login
git stash pop
```

---

# 33. Common Mistakes

## Mistake 1 — Forgetting to check status

Use:

```bash
git status
```

often.

---

## Mistake 2 — Committing secrets

Never commit:

```text
.env
passwords
API keys
private keys
credentials
```

Use `.gitignore`.

---

## Mistake 3 — Using `git add .` without checking

Before:

```bash
git add .
```

consider:

```bash
git status
git diff
```

Then:

```bash
git add .
git diff --staged
```

---

## Mistake 4 — Confusing `pull` and `fetch`

```text
fetch → download remote information
pull  → fetch + integrate
```

---

## Mistake 5 — Using reset --hard casually

```bash
git reset --hard
```

can destroy uncommitted work.

---

## Mistake 6 — Force pushing shared history

Avoid:

```bash
git push --force
```

when possible.

Prefer:

```bash
git push --force-with-lease
```

when force-pushing is genuinely required.

---

## Mistake 7 — Thinking `.gitignore` removes tracked files

It does not.

Use:

```bash
git rm --cached <file>
```

---

## Mistake 8 — Huge commits

Prefer:

```text
Add login form
Fix login validation
Add login API integration
```

instead of:

```text
Update everything
```

---

# 34. Dangerous Commands

Treat these carefully:

```bash
git reset --hard
```

```bash
git clean -fd
```

```bash
git push --force
```

```bash
git push --force-with-lease
```

```bash
git branch -D <branch>
```

```bash
git stash clear
```

---

## `git clean`

Preview untracked files that would be removed:

```bash
git clean -n
```

Remove untracked files:

```bash
git clean -f
```

Remove untracked files and directories:

```bash
git clean -fd
```

Ignored files require additional flags:

```bash
git clean -fdx
```

**Be extremely careful with `-x`.**

---

# 35. Recovery

Git is often recoverable because commits and references can remain accessible even after you move a branch pointer.

## Reflog

```bash
git reflog
```

This shows where HEAD and branch references have recently pointed.

Example:

```text
abc123 HEAD@{0}: commit: Add feature
def456 HEAD@{1}: reset: moving to HEAD~1
abc123 HEAD@{2}: commit: Add feature
```

If you accidentally reset away a commit, reflog may help you find it.

---

## Recover using reflog

Find the lost commit:

```bash
git reflog
```

Then:

```bash
git reset --hard <commit>
```

or create a recovery branch:

```bash
git switch -c recovery <commit>
```

Creating a recovery branch is safer if you are unsure.

---

# 36. Intermediate Commands

## Show current branch

```bash
git branch --show-current
```

---

## Show repository root

```bash
git rev-parse --show-toplevel
```

---

## Show current commit

```bash
git rev-parse HEAD
```

---

## Show abbreviated current commit

```bash
git rev-parse --short HEAD
```

---

## Remove a tracked file

```bash
git rm file.txt
```

This deletes the file and stages the deletion.

---

## Stop tracking a file but keep it locally

```bash
git rm --cached file.txt
```

---

## Rename a file

```bash
git mv old.txt new.txt
```

Equivalent conceptually to:

```bash
mv old.txt new.txt
git add new.txt
git rm old.txt
```

---

## See files tracked by Git

```bash
git ls-files
```

---

## Find repository objects

```bash
git fsck
```

Mostly useful for advanced recovery/debugging.

---

# 37. Quick Reference Tables

## Most important commands

| Goal | Command |
|---|---|
| Check status | `git status` |
| Initialize repo | `git init` |
| Clone repo | `git clone <url>` |
| Stage file | `git add <file>` |
| Stage everything | `git add .` |
| Commit | `git commit -m "message"` |
| View history | `git log --oneline` |
| Create branch | `git switch -c <branch>` |
| Switch branch | `git switch <branch>` |
| Merge | `git merge <branch>` |
| Fetch | `git fetch` |
| Pull | `git pull` |
| Push | `git push` |
| Add remote | `git remote add origin <url>` |
| Show remotes | `git remote -v` |
| Unstage | `git restore --staged <file>` |
| Discard file changes | `git restore <file>` |
| Temporarily save changes | `git stash` |
| Apply stash | `git stash pop` |
| Undo shared commit | `git revert <commit>` |
| Show graph | `git log --oneline --graph --all` |

---

## Undo cheat sheet

| Situation | Command |
|---|---|
| Discard unstaged file changes | `git restore <file>` |
| Discard all unstaged changes | `git restore .` |
| Unstage file | `git restore --staged <file>` |
| Undo latest commit, keep staged | `git reset --soft HEAD~1` |
| Undo latest commit, keep files unstaged | `git reset HEAD~1` |
| Undo latest commit and discard work | `git reset --hard HEAD~1` |
| Safely undo a shared commit | `git revert <commit>` |
| Recover previous HEAD | `git reflog` |

---

## Remote cheat sheet

| Goal | Command |
|---|---|
| Show remotes | `git remote -v` |
| Add remote | `git remote add origin <url>` |
| Change remote | `git remote set-url origin <url>` |
| Fetch | `git fetch` |
| Fetch all/prune | `git fetch --all --prune` |
| Pull | `git pull` |
| Push | `git push` |
| First push | `git push -u origin <branch>` |
| Delete remote branch | `git push origin --delete <branch>` |

---

## Branch cheat sheet

| Goal | Command |
|---|---|
| List branches | `git branch` |
| List all branches | `git branch -a` |
| Create branch | `git branch <name>` |
| Create + switch | `git switch -c <name>` |
| Switch | `git switch <name>` |
| Delete | `git branch -d <name>` |
| Force delete | `git branch -D <name>` |
| Rename current | `git branch -m <new-name>` |
| Show tracking | `git branch -vv` |

---

## Merge vs Rebase vs Revert vs Reset

| Command | Main purpose | Rewrites existing history? |
|---|---|---|
| `merge` | Combine branches | Usually no |
| `rebase` | Move/replay commits | Yes |
| `revert` | Undo a commit with a new commit | No |
| `reset` | Move branch/HEAD backward | Yes |

### Easy rule

```text
Need to combine branches?
→ merge

Need a cleaner private history?
→ rebase

Need to undo a shared commit?
→ revert

Need to rewrite your local history?
→ reset/rebase
```

---

# 38. One-Page Daily Cheat Sheet

If you only remember one section, remember this.

## Start a project

```bash
git init
```

## Clone a project

```bash
git clone <url>
```

## Check what's happening

```bash
git status
```

## See your changes

```bash
git diff
```

## Stage

```bash
git add .
```

## Check staged changes

```bash
git diff --staged
```

## Commit

```bash
git commit -m "Describe the change"
```

## See history

```bash
git log --oneline --graph --decorate --all
```

## Create a feature branch

```bash
git switch -c feature/my-feature
```

## Switch branches

```bash
git switch main
```

## Update from remote

```bash
git pull
```

## Upload commits

```bash
git push
```

## First push of a new branch

```bash
git push -u origin feature/my-feature
```

## Temporarily save work

```bash
git stash
```

## Restore stash

```bash
git stash pop
```

## Unstage

```bash
git restore --staged <file>
```

## Discard local changes

```bash
git restore <file>
```

## Undo a shared commit

```bash
git revert <commit>
```

## See previous HEAD locations

```bash
git reflog
```

---

# Git Mental Model to Memorize

```text
                 EDIT
                  |
                  v
          ┌─────────────────┐
          │ Working Directory│
          └────────┬────────┘
                   |
                git add
                   |
                   v
          ┌─────────────────┐
          │  Staging Area   │
          └────────┬────────┘
                   |
               git commit
                   |
                   v
          ┌─────────────────┐
          │ Local Repository│
          └────────┬────────┘
                   |
                git push
                   |
                   v
          ┌─────────────────┐
          │ Remote (GitHub) │
          └─────────────────┘
```

And remember:

```text
git status       → "What's happening?"
git diff         → "What changed?"
git add          → "Include this in next commit."
git commit       → "Save a snapshot."
git log          → "What happened before?"
git switch       → "Move to another branch."
git merge        → "Combine branches."
git fetch        → "Get remote information."
git pull         → "Get + integrate remote changes."
git push         → "Send my commits."
git stash        → "Temporarily put my work aside."
git revert       → "Undo with a new commit."
git reset        → "Move history/HEAD."
git reflog       → "Where did HEAD go?"
```

---

# Final Mental Checklist

Before committing:

```bash
git status
git diff
git add .
git diff --staged
git commit -m "Clear message"
```

Before pushing:

```bash
git log --oneline -5
git status
git push
```

When something goes wrong:

```bash
git status
git diff
git reflog
```

**Most important principle:**

> Don't memorize every Git command. Understand where your changes are, then choose the command that moves them to where you want them.
