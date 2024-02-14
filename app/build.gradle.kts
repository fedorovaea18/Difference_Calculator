plugins {
    application
    checkstyle
    jacoco
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
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.4")
    testImplementation("org.junit.platform:junit-platform-launcher:1.8.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.4")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    implementation ("info.picocli:picocli:4.7.5")
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.14.2")
    testImplementation ("org.assertj:assertj-core:3.21.0")
}


tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    reports {
        xml.required = true
    }
}
