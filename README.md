# jenkins_libs

Jenkins Shared Library with reusable pipeline steps.

## Structure

- `vars/` global pipeline steps
- `src/` reusable Groovy classes
- `resources/` bundled static resources

## Available Steps

- `checkoutGit { ... }` clones the repository using `REPOSITORY_URL` and `BRANCH`
- `testFlutterAgent()` runs `flutter --version`

## Usage

```groovy
@Library('jenkins_libs') _

pipeline {
  agent { label 'flutter' }

  stages {
    stage('Checkout') {
      steps {
        checkoutGit {
          REPOSITORY_URL = 'https://github.com/ivomika/kadro_app.git'
          BRANCH = 'master'
        }
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
