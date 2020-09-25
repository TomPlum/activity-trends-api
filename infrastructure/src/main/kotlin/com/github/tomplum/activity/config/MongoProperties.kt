package com.github.tomplum.activity.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.data.mongodb")
data class MongoProperties(val uri: String, val database: String)