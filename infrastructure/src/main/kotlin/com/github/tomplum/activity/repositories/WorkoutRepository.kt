package com.github.tomplum.activity.repositories

import com.github.tomplum.activity.config.HealthDataConfig
import com.github.tomplum.activity.converters.health.WorkoutSessionConverter
import com.github.tomplum.activity.reader.XMLReader
import com.github.tomplum.activity.workout.WorkoutSession
import com.github.tomplum.activity.xml.health.AppleHealthData
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import kotlin.time.ExperimentalTime

@Component
@ExperimentalTime
open class WorkoutRepository(
    private val reader: XMLReader,
    private val converter: WorkoutSessionConverter,
    private val properties: HealthDataConfig
) {
    @Cacheable(cacheNames = ["AppleHealthData"])
    open fun read(): List<WorkoutSession> {
        val filePath = properties.exportPath
        val data = reader.read<AppleHealthData>(filePath)
        return converter.convert(data)
    }
}