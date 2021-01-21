package com.github.tomplum.activity.converters.health

import com.github.tomplum.activity.dto.health.AppleHealthData
import com.github.tomplum.activity.workout.Distance
import com.github.tomplum.activity.workout.Energy
import com.github.tomplum.activity.workout.WorkoutSession
import com.github.tomplum.activity.workout.WorkoutType
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@Component
@ExperimentalTime
class WorkoutSessionConverter: Converter<AppleHealthData, List<WorkoutSession>> {
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z")

    override fun convert(source: AppleHealthData): List<WorkoutSession> = source.workouts.map { workout ->
        WorkoutSession(
            getWorkoutType(workout.workoutActivityType),
            getDuration(workout.duration, workout.durationUnit),
            getDistance(workout.totalDistance, workout.totalDistanceUnit),
            getEnergy(workout.totalEnergyBurned, workout.totalEnergyBurnedUnit),
            LocalDateTime.parse(workout.startDate ?: "NULL", dateFormatter),
            LocalDateTime.parse(workout.endDate ?: "NULL", dateFormatter)
        )
    }

    private fun getWorkoutType(type: String?): WorkoutType = when(type) {
        "HKWorkoutActivityTypeElliptical" -> WorkoutType.ELLIPTICAL
        "HKWorkoutActivityTypeCycling" -> WorkoutType.CYCLING
        "HKWorkoutActivityTypeWalking" -> WorkoutType.WALKING
        "HKWorkoutActivityTypeTennis" -> WorkoutType.TENNIS
        "HKWorkoutActivityTypeFunctionalStrengthTraining" -> WorkoutType.FUNCTIONAL_STRENGTH_TRAINING
        "HKWorkoutActivityTypeTraditionalStrengthTraining" -> WorkoutType.TRADITIONAL_STRENGTH_TRAINING
        "HKWorkoutActivityTypeRunning" -> WorkoutType.RUNNING
        "HKWorkoutActivityTypeCoreTraining" -> WorkoutType.CORE_TRAINING
        "HKWorkoutActivityTypeHiking" -> WorkoutType.HIKING
        "HKWorkoutActivityTypeYoga" -> WorkoutType.YOGA
        else -> WorkoutType.UNKNOWN
    }

    private fun getDuration(value: String?, unit: String?): Duration = when(unit) {
        "min" -> value?.toDoubleOrNull()?.toDuration(DurationUnit.MINUTES) ?: Duration.ZERO
        else -> throw IllegalArgumentException("Unknown Workout Duration Unit ($unit)")
    }

    private fun getDistance(value: String?, unit: String?): Distance = when(unit) {
        "km" -> Distance(unit, value?.toDoubleOrNull() ?: 0.0)
        else -> throw IllegalArgumentException("Unknown Workout Distance Unit ($unit)")
    }

    private fun getEnergy(value: String?, unit: String?): Energy = when(unit) {
        "kcal" -> Energy(unit, value?.toDoubleOrNull() ?: 0.0)
        else -> throw IllegalArgumentException("Unknown Workout Energy Unit ($unit)")
    }
}