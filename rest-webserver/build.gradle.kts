plugins {
    kotlin("jvm") version Dependencies.kotlinVersion
    kotlin("plugin.serialization") version Dependencies.kotlinVersion
}

repositories {
    mavenCentral()
}


repositories {
    mavenLocal()
    jcenter()
    maven("https://kotlin.bintray.com/ktor")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":rest-common"))

    implementation("ch.qos.logback:logback-classic:${Dependencies.logbackVersion}")
    implementation("io.ktor:ktor-server-core:${Dependencies.ktorVersion}")
    implementation("io.ktor:ktor-locations:${Dependencies.ktorVersion}")
    implementation("io.ktor:ktor-server-host-common:${Dependencies.ktorVersion}")
    testImplementation("io.ktor:ktor-server-tests:${Dependencies.ktorVersion}")
    implementation("io.ktor:ktor-server-jetty:${Dependencies.ktorVersion}")

    implementation("org.jetbrains.exposed:exposed-core:${Dependencies.exposedVersion}")
    implementation("org.jetbrains.exposed:exposed-dao:${Dependencies.exposedVersion}")
    implementation("org.jetbrains.exposed:exposed-jdbc:${Dependencies.exposedVersion}")

    implementation("com.zaxxer:HikariCP:${Dependencies.hikariVersion}")
    runtimeOnly("org.postgresql:postgresql:${Dependencies.postgreVersion}")
}
