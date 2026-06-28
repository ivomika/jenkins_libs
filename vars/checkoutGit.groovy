def call(String gitUri) {
    if (!gitUri?.trim()) {
        error('checkoutGit requires a non-empty git URI')
    }

    git(url: gitUri.trim())
}
