plugins {
    id("java")
//    id("com.gradleup.shadow") version "9.3.1" // 请根据你的 Gradle 版本选择合适版本
}

group = "com.newzhxu"
version = "unspecified"

repositories {
    mavenCentral()
}

//tasks.shadowJar {
//
//    archiveClassifier.set("") // 生成的 jar 包不带 -all 后缀
//
//    // 关键配置：重命名 Byte Buddy 及其相关类
//    relocate("net.bytebuddy", "com.newzhxu.repackaged.bytebuddy")
//// 显式指定 Manifest，Agent 必须包含这些
//    manifest {
//        attributes(
//            "Premain-Class" to "com.newzhxu.agent.Agent",
//            "Can-Redefine-Classes" to "true",
//            "Can-Retransform-Classes" to "true"
//        )
//    }
//    // 排除掉一些没用的签名文件，防止干扰
//    exclude("META-INF/maven/**")
//    exclude("META-INF/*.SF")
//    exclude("META-INF/*.DSA")
//    exclude("META-INF/*.RSA")
//}
tasks.jar {
    manifest {
        attributes["Premain-Class"] = "com.newzhxu.agent.Agent"
        attributes["Agent-Class"] = "com.newzhxu.agent.Agent"
        attributes["Can-Redefine-Classes"] = "true"
        attributes["Can-Retransform-Classes"] = "true"

    }
    // 2. 将依赖也打包进去 (关键！)
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}


dependencies {
    implementation(libs.byte.buddy)
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}