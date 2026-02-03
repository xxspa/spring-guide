import com.github.gradle.node.npm.task.NpmTask

plugins {
    id("com.github.node-gradle.node") version "7.1.0"  // 2025-2026 最新稳定版
}
group = "com.newzhxu"
version = "unspecified"
node {
    version = "24.13.0"          // 或 22.x、18.x 等你需要的 Node 版本
    pnpmVersion = "10.28.2"       // 指定 pnpm 版本（强烈建议写上）
    download = true              // 自动下载 Node 和 pnpm
    nodeProjectDir.set(file(".")) // 当前目录就是前端项目根目录
//    useCorepack.set(true)        // 推荐开启，利用 Corepack 管理包管理器
}

tasks.pnpmInstall {
    args.addAll("--loglevel=debug", "--reporter=ndjson")
}
tasks.register<NpmTask>("frontendBuild") {
    dependsOn(tasks.pnpmInstall)
    args.set(listOf("run", "build")) // 假设你的前端构建脚本是 "build"
}
tasks.register<NpmTask>("frontendDev") {
    dependsOn(tasks.pnpmInstall)
    args.set(listOf("run", "dev")) // 假设你的前端构建脚本是 "build"
}


