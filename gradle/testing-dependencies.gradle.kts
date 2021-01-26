dependencies {
    "testImplementation"("org.junit.jupiter:junit-jupiter-api:5.3.1")
    "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:5.3.1")
    "testImplementation"("org.junit.jupiter:junit-jupiter-params:5.4.2")
    "testImplementation"("org.junit.platform:junit-platform-launcher:1.3.1")
    "testImplementation"("com.willowtreeapps.assertk:assertk-jvm:0.20")
    //"testImplementation"("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    //"testImplementation"("org.mockito:mockito-junit-jupiter:3.7.7")
    //"testImplementation"("org.mockito:mockito-core:3.7.7")
    "testImplementation"("io.mockk:mockk:1.10.5")
}

tasks.withType<Test> {
    useJUnitPlatform { }
}