package com.github.tomplum.activity.repositories

import com.github.tomplum.activity.cache.FileCache
import com.github.tomplum.activity.config.HealthDataConfig
import com.github.tomplum.activity.converters.health.WorkoutSessionConverter
import com.github.tomplum.activity.xml.health.AppleHealthData
import com.github.tomplum.activity.reader.XMLReader
import com.github.tomplum.activity.workout.WorkoutSession
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import kotlin.time.ExperimentalTime

@Component
@ExperimentalTime
open class WorkoutRepository(
    private val reader: XMLReader,
    private val converter: WorkoutSessionConverter,
    private val cache: FileCache<AppleHealthData>,
    private val properties: HealthDataConfig
) {
    fun read(): List<WorkoutSession> {
        /*val data: AppleHealthData = cache.query() ?: reader.read(properties.exportPath)
        cache.store(data)*/
        return converter.convert(reader.read(properties.exportPath))
    }
}