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
        attributes("Main-Class" to "com.newzhxu.sentinel.Demo")
    }
    // 关键语法：将运行时依赖解压并打包
    val dependencies = configurations.runtimeClasspath.get()
        .map { if (it.isDirectory) it else zipTree(it) }

    from(dependencies)

    // 排除重复文件防止报错
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
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