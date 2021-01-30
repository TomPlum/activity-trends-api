package com.github.tomplum.activity.converters.health

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.github.tomplum.activity.workout.Distance
import com.github.tomplum.activity.workout.Energy
import com.github.tomplum.activity.workout.Temperature
import com.github.tomplum.activity.workout.TemperatureUnit.*
import com.github.tomplum.activity.workout.WorkoutType
import com.github.tomplum.activity.xml.health.AppleHealthData
import com.github.tomplum.activity.xml.health.MetadataEntry
import com.github.tomplum.activity.xml.health.Workout
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullAndEmptySource
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDateTime
import java.time.format.DateTimeParseException
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@ExperimentalTime
class WorkoutSessionConverterTest {

    private val converter = WorkoutSessionConverter()

    @Nested
    inner class WorkoutTypeConversion {
        @Test
        fun elliptical() {
            val data = getDataWithWorkoutType("HKWorkoutActivityTypeElliptical")
            val response = converter.convert(data)
            assertThat(response[0].type).isEqualTo(WorkoutType.ELLIPTICAL)
        }

        @Test
        fun cycling() {
            val data = getDataWithWorkoutType("HKWorkoutActivityTypeCycling")
            val response = converter.convert(data)
            assertThat(response[0].type).isEqualTo(WorkoutType.CYCLING)
        }

        @Test
        fun walking() {
            val data = getDataWithWorkoutType("HKWorkoutActivityTypeWalking")
            val response = converter.convert(data)
            assertThat(response[0].type).isEqualTo(WorkoutType.WALKING)
        }

        @Test
        fun tennis() {
            val data = getDataWithWorkoutType("HKWorkoutActivityTypeTennis")
            val response = converter.convert(data)
            assertThat(response[0].type).isEqualTo(WorkoutType.TENNIS)
        }

        @Test
        fun `Functional Strength Training`() {
            val data = getDataWithWorkoutType("HKWorkoutActivityTypeFunctionalStrengthTraining")
            val response = converter.convert(data)
            assertThat(response[0].type).isEqualTo(WorkoutType.FUNCTIONAL_STRENGTH_TRAINING)
        }

        @Test
        fun `Traditional Strength Training`() {
            val data = getDataWithWorkoutType("HKWorkoutActivityTypeTraditionalStrengthTraining")
            val response = converter.convert(data)
            assertThat(response[0].type).isEqualTo(WorkoutType.TRADITIONAL_STRENGTH_TRAINING)
        }

        @Test
        fun running() {
            val data = getDataWithWorkoutType("HKWorkoutActivityTypeRunning")
            val response = converter.convert(data)
            assertThat(response[0].type).isEqualTo(WorkoutType.RUNNING)
        }

        @Test
        fun `Core Training`() {
            val data = getDataWithWorkoutType("HKWorkoutActivityTypeCoreTraining")
            val response = converter.convert(data)
            assertThat(response[0].type).isEqualTo(WorkoutType.CORE_TRAINING)
        }

        @Test
        fun hiking() {
            val data = getDataWithWorkoutType("HKWorkoutActivityTypeHiking")
            val response = converter.convert(data)
            assertThat(response[0].type).isEqualTo(WorkoutType.HIKING)
        }

        @Test
        fun yoga() {
            val data = getDataWithWorkoutType("HKWorkoutActivityTypeYoga")
            val response = converter.convert(data)
            assertThat(response[0].type).isEqualTo(WorkoutType.YOGA)
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = ["HKWorkoutActivityTypeBanana", "Running"])
        fun `Unknown Workout Type`(type: String?) {
            val data = getDataWithWorkoutType(type)
            val response = converter.convert(data)
            assertThat(response[0].type).isEqualTo(WorkoutType.UNKNOWN)
        }

        private fun getDataWithWorkoutType(type: String?): AppleHealthData {
            val data = getValidData()
            data.workouts[0].type = type
            return data
        }
    }

    @Nested
    inner class DurationConversion {
        @Test
        fun `Valid unit & value`() {
            val data = getDataWithDuration("21.28366451660792", "min")
            val response = converter.convert(data)
            assertThat(response[0].duration).isEqualTo(21.28366451660792.toDuration(DurationUnit.MINUTES))
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = ["21.121.434", "Hello"])
        fun `Valid unit, but invalid value`(value: String?) {
            val data = getDataWithDuration(value, "min")
            val response = converter.convert(data)
            assertThat(response[0].duration).isEqualTo(Duration.ZERO)
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = ["sec", "hours"])
        fun `Invalid unit`(unit: String?) {
            val data = getDataWithDuration("21.28366451660792", unit)
            val e = assertThrows<IllegalArgumentException> { converter.convert(data) }
            assertThat(e.message).isEqualTo("Unknown Workout Duration Unit ($unit)")
        }

        private fun getDataWithDuration(value: String?, unit: String?): AppleHealthData {
            val data = getValidData()
            data.workouts[0].duration = value
            data.workouts[0].durationUnit = unit
            return data
        }
    }

    @Nested
    inner class DistanceConversion {
        @Test
        fun `Valid unit & value`() {
            val data = getDataWithDistance("2.8254995231512", "km")
            val response = converter.convert(data)
            assertThat(response[0].distance).isEqualTo(Distance("km", 2.8254995231512))
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = ["21.121.434", "Hello"])
        fun `Valid unit, but invalid value`(value: String?) {
            val data = getDataWithDistance(value, "km")
            val response = converter.convert(data)
            assertThat(response[0].distance).isEqualTo(Distance("km", 0.0))
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = ["miles", "meters"])
        fun `Invalid unit`(unit: String?) {
            val data = getDataWithDistance("2.8254995231512", unit)
            val e = assertThrows<IllegalArgumentException> { converter.convert(data) }
            assertThat(e.message).isEqualTo("Unknown Workout Distance Unit ($unit)")
        }

        private fun getDataWithDistance(value: String?, unit: String?): AppleHealthData {
            val data = getValidData()
            data.workouts[0].totalDistance = value
            data.workouts[0].totalDistanceUnit = unit
            return data
        }
    }

    @Nested
    inner class EnergyConversion {
        @Test
        fun `Valid unit & value`() {
            val data = getDataWithEnergy("209.5706979793951", "kcal")
            val response = converter.convert(data)
            assertThat(response[0].energyBurned).isEqualTo(Energy("kcal", 209.5706979793951))
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = ["21.121.434", "Hello"])
        fun `Valid unit, but invalid value`(value: String?) {
            val data = getDataWithEnergy(value, "kcal")
            val response = converter.convert(data)
            assertThat(response[0].energyBurned).isEqualTo(Energy("kcal", 0.0))
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = ["J", "magic"])
        fun `Invalid unit`(unit: String?) {
            val data = getDataWithEnergy("209.5706979793951", unit)
            val e = assertThrows<IllegalArgumentException> { converter.convert(data) }
            assertThat(e.message).isEqualTo("Unknown Workout Energy Unit ($unit)")
        }

        private fun getDataWithEnergy(value: String?, unit: String?): AppleHealthData {
            val data = getValidData()
            data.workouts[0].totalEnergyBurned = value
            data.workouts[0].totalEnergyBurnedUnit = unit
            return data
        }
    }

    @Nested
    inner class StartDateConversion {
        @Test
        fun `Valid Date Format`() {
            val data = getDataWithStartDate("2020-03-22 13:15:30 +0100")
            val response = converter.convert(data)
            assertThat(response[0].startTime).isEqualTo(LocalDateTime.of(2020, 3, 22, 13, 15, 30))
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = ["2020-03-22", "2020-03-22 13:15:30", "2020-03-22T13:15:30 +0100"])
        fun `Invalid Date Format`(value: String?) {
            val data = getDataWithStartDate(value)
            assertThrows<DateTimeParseException> { converter.convert(data) }
        }

        private fun getDataWithStartDate(value: String?): AppleHealthData {
            val data = getValidData()
            data.workouts[0].startDate = value
            return data
        }
    }

    @Nested
    inner class EndDateConversion {
        @Test
        fun `Valid Date Format`() {
            val data = getDataWithEndDate("2020-03-22 13:37:27 +0100")
            val response = converter.convert(data)
            assertThat(response[0].endTime).isEqualTo(LocalDateTime.of(2020, 3, 22, 13, 37, 27))
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = ["2020-03-22", "22020-03-22 13:37:27", "22020-03-22T13:37:27 +0100"])
        fun `Invalid Date Format`(value: String?) {
            val data = getDataWithEndDate(value)
            assertThrows<DateTimeParseException> { converter.convert(data) }
        }

        private fun getDataWithEndDate(value: String?): AppleHealthData {
            val data = getValidData()
            data.workouts[0].endDate = value
            return data
        }
    }

    @Nested
    inner class TimeZoneMetadataConversion {
        @Test
        fun `Valid time zone entry`() {
            val data = getValidData()
            val response = converter.convert(data)
            assertThat(response[0].timezone).isEqualTo("Europe/London")
        }

        @Test
        fun `Workout does not contain a nested MetadataEntry object`() {
            val data = getValidData()
            val response = converter.convert(data)
            assertThat(response[1].timezone).isNull()
        }
    }

    @Nested
    inner class WeatherTemperatureMetadataConversion {
        @Test
        fun `Valid entry with Fahrenheit unit`() {
            val data = getValidData()
            val response = converter.convert(data)
            assertThat(response[0].temperature).isEqualTo(Temperature(33, DEGREES_FAHRENHEIT, 7400))
        }

        @Test
        fun `Valid entry with Celsius unit`() {
            val data = getValidDataWithTemperature("15 degC")
            val response = converter.convert(data)
            assertThat(response[0].temperature).isEqualTo(Temperature(15, DEGREES_CELSIUS, null))
        }

        @Test
        fun `Invalid temperature unit`() {
            val data = getValidDataWithTemperature("25 degH")
            val response = converter.convert(data)
            assertThat(response[0].temperature).isEqualTo(Temperature(25, UNKNOWN, null))
        }

        @Test
        fun `Workout contains no humidity metadata`() {
            val data = getValidData()
            val response = converter.convert(data)
            assertThat(response[1].temperature?.humidity).isNull()
        }

        @Test
        fun `Workout contains no temperature metadata`() {
            val data = getValidData()
            val response = converter.convert(data)
            assertThat(response[1].temperature).isNull()
        }

        private fun getValidDataWithTemperature(value: String?): AppleHealthData {
            val data = getValidData()
            data.workouts[0].metedata = listOf(MetadataEntry("HKWeatherTemperature", value))
            return data
        }
    }

    private fun getValidData(): AppleHealthData {
        val data = AppleHealthData()
        data.workouts = mutableListOf(
            Workout(
                type = "HKWorkoutActivityTypeElliptical",
                duration = "16.36445068319638",
                durationUnit = "min",
                totalDistance = "0",
                totalDistanceUnit = "km",
                totalEnergyBurned = "177.234",
                totalEnergyBurnedUnit = "kcal",
                sourceName = "Tom’s Apple Watch",
                sourceVersion = "4.0",
                device = null,
                creationDate = "2017-10-02 20:10:35 +0100",
                startDate = "2017-10-02 19:54:13 +0100",
                endDate = "2017-10-02 20:10:35 +0100",
                events = emptyList(),
                routes = emptyList(),
                metedata = mutableListOf(
                    MetadataEntry(key = "HKTimeZone", value = "Europe/London"),
                    MetadataEntry(key = "HKWeatherTemperature", value = "33 degF"),
                    MetadataEntry(key = "HKWeatherHumidity", value = "7400 %")
                )
            ),
            Workout(
                type = "HKWorkoutActivityTypeTraditionalStrengthTraining",
                duration = "46.83198991815249",
                durationUnit = "min",
                totalDistance = "0",
                totalDistanceUnit = "km",
                totalEnergyBurned = "242.7502267543028",
                totalEnergyBurnedUnit = "kcal",
                sourceName = "Tom’s Apple Watch S4",
                sourceVersion = "6.1",
                device = "<<HKDevice: 0x280d77750>, name:Apple Watch, manufacturer:Apple Inc., model:Watch, hardware:Watch4,2, software:6.1>",
                creationDate = "2019-11-02 16:44:43 +0100",
                startDate = "2019-11-02 15:57:52 +0100",
                endDate = "2019-11-02 16:44:42 +0100",
                events = emptyList(),
                routes = emptyList()
            )
        )
        return data
    }
}