pipeline {
  agent any
  tools {
      terraform "1.0.0"
  }

  stages {
    stage('Git Checkout') {
      steps {
        git credentialsId: 'test', url: 'https://github.com/fpereyrap/terraform-jk-test'
      }
    }

    stage('Terraform Init') {
      steps {
        sh label: '', script: 'terraform init'
      }
    }
    
    stage('Terraform apply') {
      steps {
        sh label: '', script: 'terraform apply --auto-approve'
      }
    }
  }
}