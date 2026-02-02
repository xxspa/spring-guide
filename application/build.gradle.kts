plugins {
    id("java")
    id("maven-publish")
    alias(libs.plugins.dependency.management)
    alias(libs.plugins.spring.boot)
}

group = "com.newzhxu"
version = "unspecified"

repositories {
    mavenLocal()
    mavenCentral()
}
val mockitoAgent = configurations.create("mockitoAgent")
dependencies {
    implementation(project(":test-starter"))
    implementation(project(":bandwagon-starter"))
    implementation(project(":cloudflare-starter"))
    implementation(project(":api"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    // Source: https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly(libs.lombok)
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    annotationProcessor(libs.lombok)
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.15") {
        exclude("org.apache.commons", "commons-lang3")
    }
    implementation("org.apache.commons:commons-lang3")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    mockitoAgent("org.mockito:mockito-core") { isTransitive = false }
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("-javaagent:${mockitoAgent.asPath}")
    jvmArgs("-Xshare:off")
}