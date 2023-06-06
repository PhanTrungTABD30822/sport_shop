node {
  stage("Clone the project") {
    git branch: 'main', url: 'https://github.com/trungthienpyf/sport_shop.git'
  }

  stage("Compilation") {
    sh "chmod +x clean install -DskipTests"
  }

  stage("Tests and Deployment") {
    stage("Runing unit tests") {
      sh "chmod +x test -Punit"
    }

  }
}