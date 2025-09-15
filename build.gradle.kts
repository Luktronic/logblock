import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("java")
    id("io.freefair.lombok") version "8.14.2"
    id("maven-publish")
    id("signing")
}

group = "eu.luktronic"
version = "0.1.0-1"

java {
    withJavadocJar()
    withSourcesJar()
    toolchain {
        languageVersion = JavaLanguageVersion.of(24);
    }
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.slf4j:slf4j-api:2.0.0")

    testImplementation("org.slf4j:slf4j-api:2.0.0")
    testImplementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.24.3")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.assertj:assertj-core:3.27.2")
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])

            pom {
                name = "LogBlock"
                description = "A Java SLF4J-based logging wrapper that allows logging fancy blocks of text."
                url = "https://github.com/Luktronic/logblock"
                licenses {
                    license {
                        name = "The Apache Software License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        name = "Luktronic"
                        email = "luktronic@gmx.at"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/Luktronic/logblock.git"
                    developerConnection = "scm:git:ssh://github.com/Luktronic/logblock.git"
                    url = "https://github.com/Luktronic/logblock"
                }
            }
        }
    }
    repositories {
        mavenLocal()
    }
}

tasks.compileJava {
    options.release = 8
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events = setOf(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED)
    }
}