plugins {
    id("java")
}

group = "eu.luktronic"
version = "0.1-1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(8);
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}