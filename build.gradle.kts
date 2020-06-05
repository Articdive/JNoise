group = "de.articdive"
version = "2.2.0-SNAPSHOT"

plugins {
    java
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    // Jetbrains annotations
    compileOnly("org.jetbrains:annotations:20.1.0")
    // JUnit testing framework
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

tasks {
    test {
        useJUnitPlatform()
    }
}

java {
    withJavadocJar()
    withSourcesJar()
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}