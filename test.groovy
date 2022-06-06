pipeline {
  agent any
  tools {
      terraform "Terraform0.13.5"
  }

  stages {
    stage('Git Checkout') {
      steps {
        url: 'https://github.com/fpereyrap/terraform-jk-test'
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