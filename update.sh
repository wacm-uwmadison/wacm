#!/bin/bash
set -x

# Step 2: Check for changes, and if there are any, commit them
  git branch --list update-feature || git checkout -b update-feature
  git add .
  git commit -m "Update website"

# Step 3: Push to the feature branch
git push origin update-feature

# Step 4: Fetch and ensure 'main' is up to date, then merge
git fetch origin
git checkout master
git merge --no-ff update-feature -m "Merging update-feature into main"
git push origin master

# Step 5: SSH into the server and pull the latest changes
ssh wacmuse@best-linux.cs.wisc.edu "cd website && git pull"