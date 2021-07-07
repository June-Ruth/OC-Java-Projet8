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
    implementation(project(":models"))
    implementation(project(":trip-pricer", "shadow"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}