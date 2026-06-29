# jenkins_libs

Jenkins Shared Library with reusable pipeline steps.

## Structure

- `vars/` global pipeline steps
- `src/` reusable Groovy classes
- `resources/` bundled static resources

## Available Steps

- `checkoutGit(branch)` clones the repository from Jenkins SCM configuration using the provided branch
- `testFlutterAgent()` runs `flutter --version`

## Usage

```groovy
@Library('jenkins_libs') _

pipeline {
  agent { label 'flutter' }

  stages {
    stage('Checkout') {
      steps {
        checkoutGit('main')
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
