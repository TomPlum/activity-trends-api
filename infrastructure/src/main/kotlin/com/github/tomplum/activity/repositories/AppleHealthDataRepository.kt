package com.github.tomplum.activity.repositories

import com.github.tomplum.activity.config.HealthDataConfig
import com.github.tomplum.activity.logging.ActivityTrendsLogger
import com.github.tomplum.activity.reader.XMLReader
import com.github.tomplum.activity.xml.health.AppleHealthData
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
open class AppleHealthDataRepository (
    private val reader: XMLReader,
    private val properties: HealthDataConfig
) {
    @Cacheable(cacheNames = ["AppleHealthData"])
    open fun read(): AppleHealthData {
        val path = properties.exportPath + properties.fileName
        ActivityTrendsLogger.info("Reading AppleHealthData from $path")
        return reader.read(path)
    }
}