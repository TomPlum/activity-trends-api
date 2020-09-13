import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

apply(from = "$rootDir/gradle/testing-dependencies.gradle.kts")

plugins {
    idea
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":infrastructure:test-support"))

    api("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.mongodb:mongo-java-driver:3.12.7")
    implementation("org.apache.commons:commons-csv:1.8")
}

sourceSets {
    create("integration-test") {
        withConvention(KotlinSourceSet::class) {
            kotlin.srcDir("src/integration-test/kotlin")
            resources.srcDir("src/integration-test/resources")
            compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
            runtimeClasspath += output + compileClasspath + sourceSets["test"].runtimeClasspath
        }
    }
}

idea {
    module {
        val testSources = testSourceDirs
        testSources.addAll(project.sourceSets.getByName("integration-test").allSource.srcDirs)
        testSources.addAll(project.sourceSets.getByName("integration-test").resources.srcDirs)
        testSourceDirs = testSources
    }
}

task<Test>("integrationTest") {
    description = "Runs the integration tests"
    group = "verification"
    testClassesDirs = sourceSets["integration-test"].output.classesDirs
    classpath = sourceSets["integration-test"].runtimeClasspath
    mustRunAfter(tasks["test"])
    useJUnitPlatform()
}