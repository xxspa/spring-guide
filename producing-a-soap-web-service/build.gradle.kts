plugins {
    id("java")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.dependency.management)
    id("com.github.bjornvester.xjc") version "1.9.0"
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
    runtimeOnly("wsdl4j:wsdl4j")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
xjc {
    xsdDir.set(layout.projectDirectory.dir("src/main/resources/META-INF/schemas"))

}

tasks.test {
    useJUnitPlatform()
}