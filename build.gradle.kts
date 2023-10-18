allprojects {
    group = "de.articdive"
    version = "4.1.0"
    description = "A Library that allows you to generate noise using different algorithms."
}

val exportedProjects = arrayListOf(
    ":core",
    ":generators",
    ":modifiers",
    ":modules",
    ":pipeline",
    ":transformers"
)

tasks {
    register<Javadoc>("global-javadoc") {
        source = files(exportedProjects.map { project(it).the<SourceSetContainer>()["main"].allJava.asFileTree }).asFileTree
        classpath = files(exportedProjects.map { project(it).the<SourceSetContainer>()["main"].compileClasspath })
        setDestinationDir(file("${buildDir}/docs/javadoc"))
    }
}