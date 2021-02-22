package com.github.tomplum.activity.converters

import com.github.tomplum.activity.workout.HealthRecord
import com.github.tomplum.activity.data.workouts.TemperatureData
import com.github.tomplum.activity.data.workouts.WorkoutMetaData
import com.github.tomplum.activity.data.workouts.WorkoutSessionData
import com.github.tomplum.activity.data.workouts.WorkoutSessionResponse
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Component
class WorkoutSessionResponseConverter: Converter<HealthRecord, WorkoutSessionResponse> {
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    override fun convert(source: HealthRecord): WorkoutSessionResponse = source.workouts.map { session ->
        WorkoutSessionData(
            type = session.type.toString(),
            duration = session.duration.inMinutes.toString(),
            distance = session.distance.value.toString(),
            energyBurned = session.energyBurned.value.toString(),
            startTime = session.startTime.format(dateFormatter),
            endTime = session.endTime.format(dateFormatter),
            meta = WorkoutMetaData(
                timeZone = session.timezone,
                temperature = if (session.temperature != null) {
                    TemperatureData(
                        value = session.temperature!!.value,
                        unit = session.temperature!!.unit.toString(),
                        humidity = session.temperature!!.humidity
                    )
                } else null
            ),
            routeName = session.routeName
        )
    }.let { sessions -> WorkoutSessionResponse(sessions) }
}