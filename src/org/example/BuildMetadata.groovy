package org.example

class BuildMetadata implements Serializable {
    static Map fromScript(def script) {
        return [
            jobName    : script.env.JOB_NAME ?: '',
            buildNumber: script.env.BUILD_NUMBER ?: '',
            buildUrl   : script.env.BUILD_URL ?: '',
            branchName : script.env.BRANCH_NAME ?: ''
        ]
    }
}
