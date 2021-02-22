package com.github.tomplum.activity.converters.health

import com.github.tomplum.activity.workout.route.ExerciseRoute
import com.github.tomplum.activity.workout.route.HealthDataSource
import com.github.tomplum.activity.workout.route.Route
import com.github.tomplum.activity.workout.route.RoutePoint
import com.github.tomplum.activity.xml.health.route.GlobalPositioningData
import org.springframework.core.convert.converter.Converter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GlobalPositioningDataConverter : Converter<GlobalPositioningData, ExerciseRoute> {
    override fun convert(source: GlobalPositioningData) = ExerciseRoute(
        data = source.track?.segment?.parts?.map { part ->
            RoutePoint(
                latitude = part.latitude?.toDoubleOrNull() ?: 0.0,
                longitude = part.longitude?.toDoubleOrNull() ?: 0.0
            )
        }.let { points -> Route(points ?: emptyList()) },
        creationDate = LocalDateTime.parse(source.metadata?.time, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")),
        source = HealthDataSource(name = source.creator ?: "N/A", version = source.version ?: "N/A")
    )
}