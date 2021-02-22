package com.github.tomplum.activity.controllers

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.tomplum.activity.annotations.EnableMocks
import com.github.tomplum.activity.converters.service.WorkoutConversionService
import com.github.tomplum.activity.data.workouts.*
import com.github.tomplum.activity.services.HealthService
import com.github.tomplum.activity.workout.HealthRecord
import com.github.tomplum.activity.workout.WorkoutSession
import com.github.tomplum.activity.workout.route.ExerciseRoute
import com.github.tomplum.activity.workout.route.HealthDataSource
import com.github.tomplum.activity.workout.route.Route
import com.github.tomplum.activity.workout.route.RoutePoint
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import java.time.LocalDateTime
import kotlin.time.ExperimentalTime

@ExperimentalTime
@EnableMocks
class WorkoutsControllerTest {

    @RelaxedMockK private lateinit var service: HealthService
    @RelaxedMockK private lateinit var conversionService: WorkoutConversionService
    @MockK private lateinit var session: WorkoutSession

    private lateinit var controller: WorkoutsController

    @BeforeEach
    internal fun setUp() {
        controller = WorkoutsController(service, conversionService)
    }

    @Nested
    inner class GetWorkoutSessions {
        private val healthRecord = HealthRecord(mutableListOf(session))
        private val workoutSessionResponse = WorkoutSessionResponse(emptyList())

        @BeforeEach
        internal fun setUp() {
            every { service.getHealthRecord() } returns healthRecord
            every {
                conversionService.convert(healthRecord, WorkoutSessionResponse::class.java)
            } returns workoutSessionResponse
        }

        @Test
        fun `Get workout sessions should call service method`() {
            controller.getWorkoutSessions()
            verify { service.getHealthRecord() }
        }

        @Test
        fun `Get workout sessions should call Spring converter`() {
            controller.getWorkoutSessions()
            verify { conversionService.convert(healthRecord, WorkoutSessionResponse::class.java) }
        }

        @Test
        fun `Get workout sessions should return 200 HTTP Status code`() {
            val response = controller.getWorkoutSessions()
            assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        }

        @Test
        fun `Get workout sessions should return converted response object wrapped in Response Entity`() {
            val response = controller.getWorkoutSessions()
            assertThat(response.body).isEqualTo(workoutSessionResponse)
        }
    }

    @Nested
    inner class GetWorkoutRoute {
        private val creationDate = LocalDateTime.now()
        private val dataSource = HealthDataSource("Apple Watch", "4.1")
        private val route = ExerciseRoute(Route(mutableListOf(RoutePoint(-2.0, 1.0))), creationDate, dataSource)
        private val workoutResponse = WorkoutRouteResponse(
            route = RouteData(mutableListOf(RoutePointData(-2.0, 1.0))),
            creationDate = creationDate.toString(),
            source = HealthSourceData("Apple Watch", "4.1")
        )

        @BeforeEach
        internal fun setUp() {
            every { service.getWorkoutExerciseRoute("test-route-name") } returns route
            every {
                conversionService.convert(route, WorkoutRouteResponse::class.java)
            } returns workoutResponse
        }

        @Test
        fun `Get workout route should call service method`() {
            val routeName = "test-route-name"
            controller.getWorkoutRoute(routeName)
            verify { service.getWorkoutExerciseRoute(routeName) }
        }

        @Test
        fun `Get workout route should call Spring converter`() {
            val routeName = "test-route-name"
            controller.getWorkoutRoute(routeName)
            verify { conversionService.convert(route, WorkoutRouteResponse::class.java) }
        }

        @Test
        fun `Get workout route should return HTTP 200 response code`() {
            val routeName = "test-route-name"
            val response = controller.getWorkoutRoute(routeName)
            assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        }

        @Test
        fun `Get workout route should return Response Entity wrapper around converter object`() {
            val routeName = "test-route-name"
            val response = controller.getWorkoutRoute(routeName)
            assertThat(response.body).isEqualTo(workoutResponse)
        }
    }
}