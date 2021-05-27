plugins {
    java
    eclipse
    idea
    id("org.springframework.boot") version("2.4.5")
    id("io.spring.dependency-management") version("1.0.11.RELEASE")
    jacoco
}

java {
    toolchain{
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

version = "1.0.0"

repositories {
    mavenCentral()
    flatDir {
        dirs("libs")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.javamoney:moneta:1.4.1")
    implementation("com.jsoniter:jsoniter:0.9.23")

    implementation(fileTree("libs"){include("*.jar")})

    testImplementation("org.springframework.boot:spring-boot-starter-test")
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
