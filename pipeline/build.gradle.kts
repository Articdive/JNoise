plugins {
    id("jnoise.publishing-conventions")
}

dependencies {
    api(project(":core"))
    api(project(":transformers"))
    api(project(":generators"))
    api(project(":modules"))
    api(project(":modifiers"))
}