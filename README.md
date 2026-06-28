# jenkins_libs

Minimal Jenkins Shared Library scaffold.

## Structure

- `vars/` global pipeline steps
- `src/` reusable Groovy classes
- `resources/` bundled static resources

## Usage

```groovy
@Library('jenkins_libs') _

pipeline {
  agent any

  stages {
    stage('Example') {
      steps {
        script {
          sayHello('team')
          def meta = buildMetadata()
          echo "Build metadata: ${meta}"
        }
      }
    }
  }
}
```
