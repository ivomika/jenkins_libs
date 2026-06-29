def call(Map config) {
    if (!config?.REPOSITORY_URL?.trim()) {
        error('checkoutGit requires REPOSITORY_URL')
    }

    if (!config?.BRANCH?.trim()) {
        error('checkoutGit requires BRANCH')
    }

    def remoteConfig = [url: config.REPOSITORY_URL.trim()]
    if (config.CREDENTIALS_ID?.trim()) {
        remoteConfig.credentialsId = config.CREDENTIALS_ID.trim()
    }

    checkout([
        $class: 'GitSCM',
        branches: [[name: normalizeBranch(config.BRANCH.trim())]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [],
        gitTool: 'Default',
        submoduleCfg: [],
        userRemoteConfigs: [remoteConfig]
    ])
}

def call(Closure body) {
    def config = new CheckoutGitConfig()
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    call([
        REPOSITORY_URL: config.REPOSITORY_URL,
        BRANCH       : config.BRANCH,
        CREDENTIALS_ID: config.CREDENTIALS_ID
    ])
}

private String normalizeBranch(String branch) {
    if (branch.startsWith('refs/') || branch.contains('*')) {
        return branch
    }

    return "*/${branch}"
}

class CheckoutGitConfig implements Serializable {
    String REPOSITORY_URL
    String BRANCH
    String CREDENTIALS_ID
}
