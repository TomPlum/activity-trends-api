package com.github.tomplum.activity.converters.health

import com.github.tomplum.activity.workout.*
import com.github.tomplum.activity.xml.health.AppleHealthData
import com.github.tomplum.activity.xml.health.MetadataEntry
import com.github.tomplum.activity.xml.health.WorkoutRoute
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
class HealthRecordConverter: Converter<AppleHealthData, HealthRecord> {
    override fun convert(source: AppleHealthData): HealthRecord = source.workouts.map { workout ->
        WorkoutSession(
            getWorkoutType(workout.type),
            getDuration(workout.duration, workout.durationUnit),
            getDistance(workout.totalDistance, workout.totalDistanceUnit),
            getEnergy(workout.totalEnergyBurned, workout.totalEnergyBurnedUnit),
            getDate(workout.startDate),
            getDate(workout.endDate),
            getTimeZone(workout.metedata),
            getWeatherTemperature(workout.metedata),
            getRouteName(workout.route)
        )
    }.let { workouts -> HealthRecord(workouts) }

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

    private fun getDate(value: String?): LocalDateTime {
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z")
        return LocalDateTime.parse(value ?: "NULL", dateFormatter)
    }

    private fun getTimeZone(meta: List<MetadataEntry>): String? {
        return meta.getMetaDataEntryValue("HKTimeZone")
    }

    private fun getWeatherTemperature(meta: List<MetadataEntry>): Temperature? {
        val string = meta.getMetaDataEntryValue("HKWeatherTemperature") ?: return null
        val data = string.split(" ")
        val value = data[0].toInt()
        val unit = when(data[1]) {
            "degF" -> TemperatureUnit.DEGREES_FAHRENHEIT
            "degC" -> TemperatureUnit.DEGREES_CELSIUS
            else -> TemperatureUnit.UNKNOWN
        }
        return Temperature(value, unit, getWeatherHumidity(meta))
    }

    private fun getWeatherHumidity(meta: List<MetadataEntry>): Int? {
        val string = meta.getMetaDataEntryValue("HKWeatherHumidity") ?: return null
        val data = string.split(" ")
        return data[0].toInt()
    }

    private fun List<MetadataEntry>.getMetaDataEntryValue(key: String): String? {
        return filter { entry -> entry.key == key }.map { entry -> entry.value }.firstOrNull()
    }

    private fun getRouteName(route: WorkoutRoute?): String? {
        return route?.fileReference?.path?.removeSuffix(".gpx")?.takeLastWhile { char -> char != '/' }
    }
}