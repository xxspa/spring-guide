plugins {
    id("java")
    alias(libs.plugins.dependency.management)
    alias(libs.plugins.spring.boot)


}

group = "com.newzhxu"
version = "unspecified"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(platform(libs.spring.ai.bom))
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.ai:spring-ai-starter-model-ollama")
    // Source: https://mvnrepository.com/artifact/org.projectlombok/lombok
    implementation(libs.lombok)
    annotationProcessor(libs.lombok)
    implementation(project(":bandwagon-starter"))
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


tasks.test {
    useJUnitPlatform()
}