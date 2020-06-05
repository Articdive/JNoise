group = "de.articdive"
version = "1.0-SNAPSHOT"

plugins {
    java
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    // Jetbrains annotations
    implementation("org.jetbrains:annotations:19.0.0")
    // JUnit testing framework
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
}

tasks {
    test {
        useJUnitPlatform()
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}