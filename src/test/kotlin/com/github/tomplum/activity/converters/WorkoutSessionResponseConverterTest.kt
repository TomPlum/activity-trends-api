package com.github.tomplum.activity.converters

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.tomplum.activity.dto.workouts.WorkoutSessionData
import com.github.tomplum.activity.dto.workouts.WorkoutSessionResponse
import com.github.tomplum.activity.workout.Distance
import com.github.tomplum.activity.workout.Energy
import com.github.tomplum.activity.workout.WorkoutSession
import com.github.tomplum.activity.workout.WorkoutType
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@ExperimentalTime
class WorkoutSessionResponseConverterTest {
    private val converter = WorkoutSessionResponseConverter()

    @Test
    fun convert() {
        val source = getSource()
        val response = converter.convert(source)
        assertThat(response).isEqualTo(getExpectedTarget())
    }

    private fun getExpectedTarget(): WorkoutSessionResponse {
        return WorkoutSessionResponse(mutableListOf(
            WorkoutSessionData("RUNNING", "21.5", "2.5", "256.7", "2021-01-02T12:20:00", "2021-01-02T12:41:30")
        ))
    }

    private fun getSource(): List<WorkoutSession> = mutableListOf(WorkoutSession(
        WorkoutType.RUNNING,
        21.5.toDuration(DurationUnit.MINUTES),
        Distance("km", 2.5),
        Energy("kcal", 256.7),
        LocalDateTime.of(2021, 1, 2, 12, 20, 0),
        LocalDateTime.of(2021, 1, 2, 12, 41, 30)
    ))

}