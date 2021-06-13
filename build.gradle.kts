plugins {
    java
    eclipse
    idea
    jacoco
}

subprojects {

    afterEvaluate {
        java {
            toolchain{
                languageVersion.set(JavaLanguageVersion.of(8))
            }
        }

        jacoco {
            toolVersion = "0.8.4"
        }

        tasks.test {
            useJUnitPlatform()
            finalizedBy(tasks.jacocoTestReport)
        }

        tasks.jacocoTestReport {
            dependsOn(tasks.test)
            reports {
                xml.isEnabled = true
                csv.isEnabled = false
                html.destination = layout.buildDirectory.dir("jacocoHtml").get().asFile
            }
        }

        tasks.jacocoTestCoverageVerification {
            violationRules {
                rule {
                    limit {
                        counter = "LINE"
                        value = "COVEREDRATIO"
                        minimum = "0.5".toBigDecimal()
                    }
                }
            }
        }

        tasks.check {
            dependsOn(tasks.jacocoTestCoverageVerification)
        }
    }

    repositories {
        mavenCentral()
        flatDir {
            dirs("libs")
        }
    }
}