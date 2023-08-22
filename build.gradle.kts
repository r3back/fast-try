plugins {
    java
    `java-library`
    `maven-publish`
    id ("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.github.r3back"
version = "0.0.3"

val copyJars = {
        file: Provider<RegularFile> -> run {
        val name = file.get().asFile.name

        val folder = rootProject.rootDir.absolutePath

        val path = "$folder/generated/$name"

        file.get().asFile.copyTo(File(path), true)
    }
}

project.extra["copyJars"] = copyJars

subprojects{
    apply(plugin = "java")
    apply(plugin = "maven-publish")
    apply(plugin = "com.github.johnrengelman.shadow")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.jetbrains:annotations:24.0.0")
    }
}

