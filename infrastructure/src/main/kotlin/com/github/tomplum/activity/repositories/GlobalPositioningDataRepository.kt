package com.github.tomplum.activity.repositories

import com.github.tomplum.activity.config.HealthDataConfig
import com.github.tomplum.activity.logging.ActivityTrendsLogger
import com.github.tomplum.activity.reader.XMLReader
import com.github.tomplum.activity.xml.health.route.GlobalPositioningData
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
open class GlobalPositioningDataRepository(private val reader: XMLReader, private val properties: HealthDataConfig) {
    @Cacheable(cacheNames = ["GlobalPositioningData"])
    open fun read(name: String): GlobalPositioningData {
        val fileName = "${properties.exportPath}${properties.workoutRoutePath}$name.gpx"
        ActivityTrendsLogger.info("Reading $fileName")
        return reader.read(fileName)
    }
}