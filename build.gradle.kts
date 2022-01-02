group = "de.articdive"
version = "3.0.2-SNAPSHOT"

plugins {
    `java-library`
    `maven-publish`
    signing
}

repositories {
    mavenCentral()
}

dependencies {
    // Jetbrains annotations
    compileOnly("org.jetbrains:annotations:23.0.0")
    // JUnit testing framework
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks {
    test {
        useJUnitPlatform()
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    withJavadocJar()
    withSourcesJar()
}

publishing {
    repositories {
        maven {
            credentials {
                username = System.getenv()["SONATYPE_USERNAME"] ?: (if (hasProperty("SONATYPE_USERNAME")) (property("SONATYPE_USERNAME") as String) else "")
                password = System.getenv()["SONATYPE_PASSWORD"] ?: (if (hasProperty("SONATYPE_PASSWORD")) (property("SONATYPE_PASSWORD") as String) else "")
            }
            val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "de.articdive"
            artifactId = "jnoise"
            version = project.version as String

            from(components["java"])
            pom {
                name.set("JNoise")
                description.set("A Library that allows you to generate noise using different algorithms.")
                url.set("https://github.com/Articdive/JNoise")
                licenses {
                    license {
                        name.set("GPL-3.0 License")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.en.html")
                    }
                }
                developers {
                    developer {
                        id.set("Articdive")
                        name.set("Articdive")
                    }
                }
                scm {
                    connection.set("scm:git:github.com/Articdive/JNoise.git")
                    developerConnection.set("scm:git:ssh://github.com/Articdive/JNoise.git")
                    url.set("https://github.com/Articdive/JNoise/tree/main")
                }
            }
        }
    }
}

signing {
    if (System.getenv()["CI"] != null) {
        useInMemoryPgpKeys(System.getenv()["SIGNING_KEY"], System.getenv()["SIGNING_PASSWORD"])
        // Only attempt to sign if we are in the CI.
        // If you are publishing to maven local then it doesn't need signing.
        sign(publishing.publications["maven"])
    }
}