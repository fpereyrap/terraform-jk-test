// import common.Constants
// import common.Helpers
// def validProducts = ['p76']

job("main/test") {
    description('Runs terraform code aiming to Azure accounts')
    wrappers {
        maskPasswords()
        timestamps()
    }
    logRotator {
        numToKeep(500)
    }
    parameters {
        textParam('TEST_PARAMETER',
        '# sample line 1 \n\
        # sample line 2',
        'Jenkins testing parameters to build with parameter')
    }
    concurrentBuild()
    properties {
        throttleJobProperty {
            throttleEnabled(true)
            maxConcurrentPerNode(0)
            maxConcurrentTotal(3)
            throttleOption('project')
            limitOneJobWithMatchingParams(true)
            paramsToUseForLimit('')
            matrixOptions{
                throttleMatrixBuilds(false)
                throttleMatrixConfigurations(false)
            }
        }
    }
    environmentVariables {
        groovy(BUILD_NAME_SCRIPT="test.groovy")
    }
    scm {
        git {
            branch("*/${realBranchName}")
            remote {
                name(REPO_NAME='terraform-jk-test')
                url('$GIT_URL')
                // credentials(Constants.REPO_CREDS)
            }
            extensions {
                cleanAfterCheckout()
            }
        }
    }

    steps {
        shell('''ls -la
ls -la
terraform init
''')
    }

}
