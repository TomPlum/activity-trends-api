package com.github.tomplum.activity.repositories

import com.github.tomplum.activity.config.HealthDataConfig
import com.github.tomplum.activity.converters.health.WorkoutSessionConverter
import com.github.tomplum.activity.dto.health.AppleHealthData
import com.github.tomplum.activity.reader.XMLReader
import com.github.tomplum.activity.workout.WorkoutSession
import org.springframework.stereotype.Repository
import kotlin.time.ExperimentalTime

@Repository
@ExperimentalTime
open class WorkoutRepository(
    private val reader: XMLReader,
    private val converter: WorkoutSessionConverter,
    private val properties: HealthDataConfig
) {
    fun read(): List<WorkoutSession> {
        //TODO: Fix Property Injection
        val path = "/media/tom/T.Plumpton 2TB Ext HDD/My Documents/Apple Health Exports/export/apple_health_export/export.xml"
        val data: AppleHealthData = reader.read(path)
        return converter.convert(data)
    }
}