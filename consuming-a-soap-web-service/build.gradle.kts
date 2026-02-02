plugins {
    id("java")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.dependency.management)
    id("uk.co.boothen.gradle.wsimport") version "0.25"
}

group = "com.newzhxu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-web-services")
    { exclude("org.springframework.boot", "spring-boot-starter-web") }
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
wsimport {
    wsdlSourceRoot = "http://localhost:8080/services/"

    wsdl("countries.wsdl") {
        packageName("com.example.consumingwebservice.wsdl")
        xjcarg("-XautoNameResolution")
    }
}

tasks.test {
    useJUnitPlatform()
}