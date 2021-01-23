package com.github.tomplum.activity.converters

import com.github.tomplum.activity.dto.workouts.WorkoutSessionData
import com.github.tomplum.activity.dto.workouts.WorkoutSessionResponse
import com.github.tomplum.activity.workout.WorkoutSession
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Component
class WorkoutSessionResponseConverter: Converter<List<WorkoutSession>, WorkoutSessionResponse> {
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    override fun convert(source: List<WorkoutSession>): WorkoutSessionResponse = source.map { session ->
        WorkoutSessionData(
            type = session.type.toString(),
            duration = session.duration.inMinutes.toString(),
            distance = session.distance.value.toString(),
            energyBurned = session.energyBurned.value.toString(),
            startTime = session.startTime.format(dateFormatter),
            endTime = session.endTime.format(dateFormatter)
        )
    }.let { sessions -> WorkoutSessionResponse(sessions) }
}