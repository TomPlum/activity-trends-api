package com.github.tomplum.activity.converters

import com.github.tomplum.activity.data.workouts.HealthSourceData
import com.github.tomplum.activity.data.workouts.RouteData
import com.github.tomplum.activity.data.workouts.RoutePointData
import com.github.tomplum.activity.data.workouts.WorkoutRouteResponse
import com.github.tomplum.activity.workout.route.ExerciseRoute
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter

@Component
class WorkoutRouteResponseConverter: Converter<ExerciseRoute, WorkoutRouteResponse> {
    override fun convert(source: ExerciseRoute) = WorkoutRouteResponse(
        route = RouteData(
            points = source.data.points.map { point -> RoutePointData(point.latitude, point.longitude) }
        ),
        creationDate = source.creationDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME ),
        source = HealthSourceData(source.source.name, source.source.version)
    )
}