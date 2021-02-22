package com.github.tomplum.activity.repositories

import com.github.tomplum.activity.converters.health.WorkoutConversionService
import com.github.tomplum.activity.workout.HealthRecord
import com.github.tomplum.activity.workout.route.ExerciseRoute
import org.springframework.stereotype.Component
import kotlin.time.ExperimentalTime

@Component
@ExperimentalTime
open class HealthDataRepository(
    private val healthDataRepository: AppleHealthDataRepository,
    private val gpsDataRepository: GlobalPositioningDataRepository,
    private val converter: WorkoutConversionService,
) {
    open fun getHealthRecord(): HealthRecord {
        val data = healthDataRepository.read()
        return converter.convert(data, HealthRecord::class.java)!!
    }

    open fun getExerciseRoute(name: String): ExerciseRoute {
        val data = gpsDataRepository.read(name)
        return converter.convert(data, ExerciseRoute::class.java)!!
    }
}