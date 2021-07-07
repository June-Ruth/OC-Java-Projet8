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
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.4")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.11.4")

}