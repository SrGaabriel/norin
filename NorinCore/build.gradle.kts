plugins {
    java
    kotlin("jvm") version Dependencies.KotlinVersion
}

repositories {
    mavenCentral()
}

dependencies {
    api(project(":rest-common"))
    api(project(":rest-wrapper"))
    compileOnly("io.papermc.paper:paper-api:1.18.1-R0.1-SNAPSHOT")
}
