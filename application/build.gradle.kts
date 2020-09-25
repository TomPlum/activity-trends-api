apply(from = "$rootDir/gradle/testing-dependencies.gradle.kts")

dependencies {
    implementation(project(":domain"))
    implementation(project(":infrastructure"))

    implementation("org.springframework:spring-context")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
}