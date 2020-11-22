group = "de.articdive"
version = "2.0.0-SNAPSHOT"

plugins {
    java
    id("maven-publish")
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "de.articdive"
            artifactId = "jnoise"
            version = "${project.version}"

            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "minestom-repo"
            url = uri("https://repo.minestom.com/repository/maven-public")
            credentials {
                username= System.getenv("MINESTOM_REPO_USERNAME")
                password= System.getenv("MINESTOM_REPO_PASSWORD")
            }
        }
    }
}