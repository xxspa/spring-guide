plugins {
    id("java")
    id("org.springframework.boot") version "3.5.9"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.newzhxu"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":test-starter"))
    implementation(project(":bandwagon-starter"))
    implementation(project(":cloudflare-starter"))
    implementation(project(":api"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    // Source: https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:1.18.42")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    annotationProcessor("org.projectlombok:lombok:1.18.42")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.15") {
        exclude("org.apache.commons", "commons-lang3")
    }
    implementation("org.apache.commons:commons-lang3")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}