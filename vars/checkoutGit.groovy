def call(String branch) {
    if (!branch?.trim()) {
        error('checkoutGit requires a non-empty branch name')
    }

    if (!binding.hasVariable('scm') || scm == null) {
        error('checkoutGit requires SCM to be configured in the Jenkins pipeline/job configuration')
    }

    def remoteConfigs = scm.userRemoteConfigs
    if (!remoteConfigs) {
        error('checkoutGit could not read Git remote configuration from the pipeline/job SCM settings')
    }

    def branchName = normalizeBranch(branch.trim())

    checkout([
        $class: 'GitSCM',
        branches: [[name: branchName]],
        doGenerateSubmoduleConfigurations: false,
        extensions: scm.extensions ?: [],
        gitTool: scm.gitTool ?: 'Default',
        submoduleCfg: [],
        userRemoteConfigs: remoteConfigs
    ])
}

private String normalizeBranch(String branch) {
    if (branch.startsWith('refs/') || branch.contains('*')) {
        return branch
    }

    return "*/${branch}"
}
