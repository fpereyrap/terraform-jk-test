terraform {
  required_providers {
    random = {
      source = "hashicorp/random"
      version = "3.3.0"
    }
  }
}

provider "random" {
  # Configuration options
}

resource "random_id" "server" {

  byte_length = 8
}

output "test" {
    value = random_id.server
}