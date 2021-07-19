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
            exclude("**/*IT.class")
        }

        tasks.jacocoTestReport {
            dependsOn(tasks.test)
            reports {
                xml.isEnabled = true
                csv.isEnabled = false
                html.destination = layout.buildDirectory.dir("jacocoHtml").get().asFile
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