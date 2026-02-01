def call() {
  withCredentials([string(credentialsId: 'nvd-api-key', variable: 'NVD_API_KEY')]) {

    catchError(buildResult: 'UNSTABLE', stageResult: 'UNSTABLE') {

      dependencyCheck(
        odcInstallation: 'OWASP',
        additionalArguments: """
          --format XML
          --out dependency-check-report
          --noupdate
          --disableAssembly
        """
      )
    }
  }

  dependencyCheckPublisher(
    pattern: 'dependency-check-report/dependency-check-report.xml'
  )
}
