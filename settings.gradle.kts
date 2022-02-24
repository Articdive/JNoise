enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}

rootProject.name = "JNoise"
include("core")
include("transformers")
include("generators")
include("modules")
include("modifiers")
include("pipeline")
