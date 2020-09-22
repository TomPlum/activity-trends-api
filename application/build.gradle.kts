apply(from = "$rootDir/gradle/testing-dependencies.gradle.kts")

dependencies {
    implementation(project(":domain"))
}