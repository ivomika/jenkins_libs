def call() {
    sh(label: 'Check Flutter version', script: 'flutter --version')
}
