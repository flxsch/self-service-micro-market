# Self service micro market

## Backend
[README.md](backend/README.md)

## Contributing guideline with git
### Step 1: Create new branch
The branch name should start with `<ISSUE_NUMBER>-`
```
git checkout -b 18-setup-monorepo-backend
 ```
### Step 2: Make your changes
The commit name should start with: `GH-<ISSUENUMBER>:`
```
git add .
git commit -m "GH-18: setup backend folder"
```
### Step 3: Rebase onto master before submitting PR
- Step 1: make sure your branch is up to date
```
git pull --rebase <remote name> <branch name>
```
- Step 2: pull changes from master
```
git rebase origin/main
```
- Step 3 (repeating): if there any conflicts, fix them
```
git add .
```
- Step 4 (repeating): add fixes of conflicts
```
git rebase --continue
```
### Step 4: Run tests
TODO:
### Step 5:
Push changes to feature branch
```
git push <remote name> <branch name> --force-with-lease
```
### Step 6: Submit PR
- Add reviewer
- Add meaningful description
- Link PR to the project
- Link PR to the issue
