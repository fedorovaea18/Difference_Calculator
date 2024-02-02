plugins {
    application
    checkstyle
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.compileJava {
    options.release = 20
}

application {
    mainClass = "hexlet.code.App"
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("info.picocli:picocli:4.7.5")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.14.0-rc1")
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.13.4")
}

tasks.test {
    useJUnitPlatform()
}

