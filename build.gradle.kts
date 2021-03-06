import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
        jcenter()
        google()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.4.21"))
    }
}

plugins {
    idea
    kotlin("jvm") version "1.4.21"
    id("org.springframework.boot") version "2.3.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("com.gorylenko.gradle-git-properties") version "2.2.3"
}

springBoot {
    mainClassName = "com.github.tomplum.activity.ApplicationKt"
}

allprojects {
    version = "1.0.0"

    apply(plugin = "kotlin")

    repositories {
        mavenCentral()
        jcenter()
        google()
    }

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))

        testImplementation(project(":test-support"))
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}

subprojects {
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "kotlin")

    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:2.3.3.RELEASE")
        }
    }
}

apply(from = "$rootDir/gradle/testing-dependencies.gradle.kts")

dependencies {
    //Layers & Modules
    implementation(project(":application"))
    implementation(project(":domain"))

    //Spring Boot
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    //implementation("org.springframework.boot:spring-boot-starter-security")

    //Swagger
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    //implementation("io.springfox:springfox-swagger2:3.0.0")
    //implementation("io.springfox:springfox-data-rest:3.0.0")
    //implementation("io.springfox:springfox-swagger-ui:3.0.0")

}