import hudson.model.*
 
for (pd in PageDecorator.all()) {
  if (pd instanceof org.codefirst.SimpleThemeDecorator) {
    pd.cssUrl = 'https://cdn.rawgit.com/afonsof/jenkins-material-theme/gh-pages/dist/material-red.css'
  }
}

