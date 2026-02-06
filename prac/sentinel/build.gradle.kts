plugins {
    id("java")
}

group = "com.newzhxu"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.alibaba.csp:sentinel-core:1.8.9")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}