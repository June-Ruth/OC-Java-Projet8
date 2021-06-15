plugins {
    java
    eclipse
    idea
    id("org.springframework.boot") version(DependenciesVersions.springBoot)
    id("io.spring.dependency-management") version(DependenciesVersions.springDependencyManagement)
    jacoco
}

version = "1.0.0"

dependencies {
    implementation(project(":reward-central", "shadow"))
    implementation(project(":gps-utils", "shadow"))
    implementation(project(":trip-pricer", "shadow"))
    implementation(project(":models"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}