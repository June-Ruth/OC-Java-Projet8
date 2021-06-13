plugins {
    java
    eclipse
    idea
    jacoco
    id("com.github.johnrengelman.shadow") version DependenciesVersions.shadowPlugin
}

version = "1.0.0"

dependencies {
    implementation(fileTree("libs"){include("*.jar")})
}


configurations {
    shadow
}

artifacts {
    shadow(tasks.shadowJar) {
        builtBy(tasks.shadowJar)
    }
}