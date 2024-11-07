import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    val kotlinVersion = "2.0.21"
    kotlin("jvm") version kotlinVersion

    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"

    kotlin("plugin.jpa") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
}

repositories {
    mavenCentral()
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")

    group = "tech.clovy"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlin {
            jvmToolchain(17)
            compilerOptions {
                languageVersion.set(KotlinVersion.KOTLIN_2_0)
                apiVersion.set(KotlinVersion.KOTLIN_2_0)
                freeCompilerArgs.addAll("-Xjsr305=strict")
            }
        }
    }

    tasks.bootJar {
        enabled = true
    }

    dependencies {
        /// SPRING BOOT
        api("org.springframework.boot:spring-boot-starter-web")

        /// KOTLIN
        implementation(kotlin("reflect"))

        /// REDIS
        implementation("org.springframework.boot:spring-boot-starter-data-redis")

        /// TEST
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")

        testImplementation(kotlin("test-junit5"))
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }
}

subprojects {
    dependencies {
        /// SPRING BOOT
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.boot:spring-boot-starter-validation")
    }
}

dependencies {
    implementation(project(":api"))
    implementation(project(":business"))
    implementation(project(":core"))
    implementation(project(":infra"))

    implementation("com.mysql:mysql-connector-j:9.1.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.jar {
    enabled = false
}
