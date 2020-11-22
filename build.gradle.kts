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
            val releaseRepoUrl = "https://repo.minestom.com/repository/maven-releases"
            val snapshotRepoUrl = "https://repo.minestom.com/repository/maven-snapshots"
            url = uri(if (version.toString().endsWith("-SNAPSHOT")) snapshotRepoUrl else releaseRepoUrl)
            credentials {
                username= System.getenv("MINESTOM_REPO_USERNAME")
                password= System.getenv("MINESTOM_REPO_PASSWORD")
            }
        }
    }
}