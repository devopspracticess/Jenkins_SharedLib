def call(String project, String imageTag) {

  withCredentials([
    usernamePassword(
      credentialsId: 'Docker',
      usernameVariable: 'DOCKER_USER',
      passwordVariable: 'DOCKER_PASS'
    )
  ]) {
    sh '''
      echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
    '''
    sh "docker push $DOCKER_USER/${project}:${imageTag}"
  }
}
