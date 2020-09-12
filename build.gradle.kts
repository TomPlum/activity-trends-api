import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
        jcenter()
        google()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.4.0"))
    }
}

plugins {
    idea
    kotlin("jvm") version "1.4.0"
    id("org.springframework.boot") version "2.3.3.RELEASE"
    id ("io.spring.dependency-management") version "1.0.8.RELEASE"
}

allprojects {
    group = "com.github.tomplum"
    version = "0.0.1-SNAPSHOT"

    apply(plugin = "kotlin")
    apply(plugin = "io.spring.dependency-management")

    repositories {
        mavenCentral()
        jcenter()
        google()
    }

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:2.3.3.RELEASE")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}