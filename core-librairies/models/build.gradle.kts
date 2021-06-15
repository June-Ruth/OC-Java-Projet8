plugins {
    java
    eclipse
    idea
    jacoco
}

version = "1.0.0"

dependencies {
    implementation(project(":gps-utils", "shadow"))
    implementation(project(":trip-pricer", "shadow"))
}