plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Removed the JUnit dependencies as your tests are using TestNG
    testImplementation("org.testng:testng:7.4.0")
    implementation("io.appium:java-client:9.2.0")
    implementation("org.seleniumhq.selenium:selenium-java:4.18.1")
}

val suite1: Boolean = project.findProperty("suite1")?.toString()?.toBoolean() ?: false

tasks.test {
    useTestNG() {
        if (suite1) {
            suites("src/test/resources/testng.xml")
        }
        outputDirectory = file("test-output")
        useDefaultListeners = true
    }
}
