# jenkins_libs

Jenkins Shared Library with reusable pipeline steps.

## Structure

- `vars/` global pipeline steps
- `src/` reusable Groovy classes
- `resources/` bundled static resources

## Available Steps

- `checkoutGit(gitUri)` clones a repository into the current workspace
- `testFlutterAgent()` runs `flutter --version`

## Usage

```groovy
@Library('jenkins_libs') _

pipeline {
  agent { label 'flutter' }

  stages {
    stage('Checkout') {
      steps {
        checkoutGit('git@github.com:your-org/your-repo.git')
      }
    }

    stage('Flutter Agent Check') {
      steps {
        testFlutterAgent()
      }
    }
  }
}
```
