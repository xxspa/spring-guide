plugins {
    id("java")
}

group = "com.newzhxu"
version = "unspecified"

repositories {
    mavenCentral()
}
tasks.jar {
    manifest {
        attributes("Main-Class" to "com.newzhxu.target.Main")
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
tasks

tasks.test {
    useJUnitPlatform()
}