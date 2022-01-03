plugins {
    kotlin("multiplatform") version Dependencies.KotlinVersion
    kotlin("plugin.serialization") version Dependencies.KotlinVersion
}

repositories {
    mavenCentral()
    maven("https://kotlin.bintray.com/ktor")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.1")
                api("io.ktor:ktor-client-core:${Dependencies.KtorVersion}")
                api("io.ktor:ktor-serialization:${Dependencies.KtorVersion}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}
