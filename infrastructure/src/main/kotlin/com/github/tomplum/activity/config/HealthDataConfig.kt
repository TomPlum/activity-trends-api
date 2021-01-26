package com.github.tomplum.activity.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("data.health")
open class HealthDataConfig(val exportPath: String)