plugins {
    id("java")
    id("io.freefair.lombok") version "8.14.2"
    id("maven-publish")
}

group = "eu.luktronic"
version = "0.1-1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(8);
    }
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
    }
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.slf4j:slf4j-api:2.0.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}