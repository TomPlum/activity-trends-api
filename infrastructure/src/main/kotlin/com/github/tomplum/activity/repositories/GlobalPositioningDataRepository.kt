package com.github.tomplum.activity.repositories

import com.github.tomplum.activity.config.HealthDataConfig
import com.github.tomplum.activity.reader.XMLReader
import com.github.tomplum.activity.xml.health.route.GlobalPositioningData
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
open class GlobalPositioningDataRepository(val reader: XMLReader, val properties: HealthDataConfig) {
    @Cacheable(cacheNames = ["GlobalPositioningData"])
    open fun read(name: String): GlobalPositioningData {
        val fileName = "$properties.exportPath$name.gpx"
        return reader.read(fileName)
    }
}