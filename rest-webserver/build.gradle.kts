plugins {
    kotlin("jvm") version Dependencies.KotlinVersion
    kotlin("plugin.serialization") version Dependencies.KotlinVersion
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

    implementation("ch.qos.logback:logback-classic:${Dependencies.LogbackVersion}")
    implementation("io.ktor:ktor-server-core:${Dependencies.KtorVersion}")
    implementation("io.ktor:ktor-locations:${Dependencies.KtorVersion}")
    implementation("io.ktor:ktor-server-host-common:${Dependencies.KtorVersion}")
    testImplementation("io.ktor:ktor-server-tests:${Dependencies.KtorVersion}")
    implementation("io.ktor:ktor-server-jetty:${Dependencies.KtorVersion}")

    implementation("org.jetbrains.exposed:exposed-core:${Dependencies.ExposedVersion}")
    implementation("org.jetbrains.exposed:exposed-dao:${Dependencies.ExposedVersion}")
    implementation("org.jetbrains.exposed:exposed-jdbc:${Dependencies.ExposedVersion}")

    implementation("com.zaxxer:HikariCP:${Dependencies.HikariVersion}")
    runtimeOnly("org.postgresql:postgresql:${Dependencies.PostgreVersion}")
}
