plugins {
    id("java")
    alias(libs.plugins.dependency.management)
    alias(libs.plugins.spring.boot)


}

group = "com.newzhxu"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(libs.spring.ai.bom))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.ai:spring-ai-starter-model-ollama")
    // Source: https://mvnrepository.com/artifact/org.projectlombok/lombok
    implementation(libs.lombok)
    annotationProcessor(libs.lombok)
    implementation(project(":bandwagon-starter"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


tasks.test {
    useJUnitPlatform()
}