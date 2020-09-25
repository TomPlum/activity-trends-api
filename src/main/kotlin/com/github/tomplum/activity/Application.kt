package com.github.tomplum.activity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = ["com.github.tomplum.activity.config"])
@EnableConfigurationProperties(value = [MongoProperties::class])
open class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}