plugins {
    kotlin("jvm") version Dependencies.kotlinVersion
    kotlin("plugin.serialization") version Dependencies.kotlinVersion
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":rest-common"))
    implementation("io.ktor:ktor-client-cio:${Dependencies.ktorVersion}")
    implementation("io.ktor:ktor-client-serialization:${Dependencies.ktorVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${Dependencies.kotlinVersion}")
}
