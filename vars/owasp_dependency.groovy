def call() {
  withCredentials([string(credentialsId: 'nvd-api-key', variable: 'NVD_API_KEY')]) {
    dependencyCheck(
      odcInstallation: 'OWASP',
      additionalArguments: """
        --scan .
        --format XML
        --out dependency-check-report
        --nvdApiKey ${NVD_API_KEY}
      """,
      failOnError: false
    )
  }

  dependencyCheckPublisher(
    pattern: 'dependency-check-report/dependency-check-report.xml'
  )
}
