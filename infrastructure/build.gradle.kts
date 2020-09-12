apply(from = "$rootDir/gradle/testing-dependencies.gradle.kts")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.mongodb:mongo-java-driver:3.12.7")
}