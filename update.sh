
  git branch --list update-feature || git checkout -b update-feature
  git add .
  git commit -m "Update website"

git push origin update-feature

git fetch origin
git checkout master
git merge --no-ff update-feature -m "Merging update-feature into main"
git push origin master

ssh wacmuse@best-linux.cs.wisc.edu "cd website && git pull"