pipeline {
  agent any
  tools {
      terraform "0.13.5"
  }

  stages {
    stage('Git Checkout') {
      steps {
        git url: 'https://github.com/fpereyrap/terraform-jk-test.git'
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