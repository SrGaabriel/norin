plugins {
    kotlin("jvm") version Dependencies.KotlinVersion
    kotlin("plugin.serialization") version Dependencies.KotlinVersion
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":rest-common"))
    implementation("io.ktor:ktor-client-cio:${Dependencies.KtorVersion}")
    implementation("io.ktor:ktor-client-serialization:${Dependencies.KtorVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${Dependencies.KotlinVersion}")
}
