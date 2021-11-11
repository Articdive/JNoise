group = "de.articdive"
version = "3.0.1"

plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    // Jetbrains annotations
    compileOnly("org.jetbrains:annotations:22.0.0")
    // JUnit testing framework
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks {
    test {
        useJUnitPlatform()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "de.articdive"
            artifactId = "jnoise"
            version = project.version as String

            from(components["java"])
        }
    }
}