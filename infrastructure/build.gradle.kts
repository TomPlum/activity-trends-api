import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

apply(from = "$rootDir/gradle/testing-dependencies.gradle.kts")

plugins {
    idea
}

dependencies {
    //Module Dependencies
    implementation(project(":domain"))
    testImplementation(project(":infrastructure:test-support"))

    //Spring
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    //CSV Reader
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