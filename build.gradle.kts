plugins {
    java
}

allprojects {
    group = "com.norin.core"
    version = "1.0-SNAPSHOT"
    repositories {
        mavenCentral()
        maven("https://papermc.io/repo/repository/maven-public/")
    }
}

dependencies {
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
