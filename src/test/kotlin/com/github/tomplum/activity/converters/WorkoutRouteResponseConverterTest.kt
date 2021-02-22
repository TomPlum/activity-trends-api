package com.github.tomplum.activity.converters

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.tomplum.activity.data.workouts.HealthSourceData
import com.github.tomplum.activity.data.workouts.RouteData
import com.github.tomplum.activity.data.workouts.RoutePointData
import com.github.tomplum.activity.data.workouts.WorkoutRouteResponse
import com.github.tomplum.activity.workout.route.ExerciseRoute
import com.github.tomplum.activity.workout.route.HealthDataSource
import com.github.tomplum.activity.workout.route.Route
import com.github.tomplum.activity.workout.route.RoutePoint
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class WorkoutRouteResponseConverterTest {
    private val converter = WorkoutRouteResponseConverter()

    @Test
    fun `Valid exercise route`() {
        val route = getValidExerciseRoute()
        val response = converter.convert(route)
        assertThat(response).isEqualTo(getExpectedTargetResponse())
    }

    private fun getValidExerciseRoute() = ExerciseRoute(
        data = Route(
            mutableListOf(RoutePoint(-2.53432, 1.23412), RoutePoint(-1.05342, 2.19345))),
        creationDate = LocalDateTime.of(2021, 2, 2, 17, 30, 0),
        source = HealthDataSource("Apple Watch S4", "4.1")
    )

    private fun getExpectedTargetResponse() = WorkoutRouteResponse(
        route = RouteData(mutableListOf(RoutePointData(-2.53432, 1.23412), RoutePointData(-1.05342, 2.19345))),
        creationDate = "2021-02-02T17:30:00",
        source = HealthSourceData("Apple Watch S4", "4.1")
    )
}