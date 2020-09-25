val sourceSets = the<SourceSetContainer>()

sourceSets {
    create("integTest") {
        java.srcDir(file("src/integTest/java"))
        resources.srcDir(file("src/integTest/resources"))
        compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
        runtimeClasspath += output + compileClasspath
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

tasks.named("check") {
    dependsOn("integrationTest")
}

