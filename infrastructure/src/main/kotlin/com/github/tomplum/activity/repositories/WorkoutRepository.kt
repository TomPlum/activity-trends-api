package com.github.tomplum.activity.repositories

import com.github.tomplum.activity.converters.health.WorkoutSessionConverter
import com.github.tomplum.activity.dto.health.AppleHealthData
import com.github.tomplum.activity.reader.XMLReader
import com.github.tomplum.activity.workout.WorkoutSession
import org.springframework.stereotype.Repository
import kotlin.time.ExperimentalTime

@Repository
@ExperimentalTime
open class WorkoutRepository constructor(
    private val reader: XMLReader,
    private val converter: WorkoutSessionConverter
) {
    fun read(): List<WorkoutSession> {
        val data: AppleHealthData = reader.read("export.xml")
        return converter.convert(data)
    }
}