plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = rootProject.group
version = rootProject.version


dependencies {
    testImplementation("junit:junit:4.13.2")

    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testImplementation("org.mockito:mockito-core:5.3.1")
    testImplementation("org.mockito:mockito-junit-jupiter:5.3.1")
}

tasks {
    shadowJar {
        archiveFileName.set("fast-try.jar")
        archiveClassifier.set("fast-try-classifier")

        relocate("org.intellij.lang.annotations", "com.qualityplus.fasttry.base.lib.org.intellij.lang.annotations")
        relocate("org.jetbrains.annotations", "com.qualityplus.fasttry.base.lib.org.jetbrains.annotations")

        doLast {
            @Suppress("UNCHECKED_CAST")
            (rootProject.ext.get("copyJars") as? ((Provider<RegularFile>) -> File) ?: return@doLast)(archiveFile)
        }
    }

    withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    withType<JavaCompile> {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
    }

    build {
        dependsOn(shadowJar)
    }
}


publishing {
    publications {
        register<MavenPublication>("maven") {
            groupId = project.group.toString()
            version = project.version.toString()
            artifactId = rootProject.name

            artifact(project.tasks.shadowJar.get().archiveFile)
        }
    }

    publishing {
        repositories {
            maven {
                name = "jitpack"
                url = uri("https://jitpack.io")
                credentials {
                    username = System.getenv("JITPACK_USERNAME")
                    password = System.getenv("JITPACK_PASSWORD")
                }
            }
        }
    }
}