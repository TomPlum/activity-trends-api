package com.github.tomplum.activity.repositories

import com.github.tomplum.activity.annotations.EnableMocks
import com.github.tomplum.activity.converters.health.WorkoutConversionService
import com.github.tomplum.activity.workout.HealthRecord
import com.github.tomplum.activity.workout.route.ExerciseRoute
import com.github.tomplum.activity.workout.route.HealthDataSource
import com.github.tomplum.activity.workout.route.Route
import com.github.tomplum.activity.workout.route.RoutePoint
import com.github.tomplum.activity.xml.health.AppleHealthData
import com.github.tomplum.activity.xml.health.route.GlobalPositioningData
import com.github.tomplum.activity.xml.health.route.MetaData
import com.github.tomplum.activity.xml.health.route.Track
import com.github.tomplum.activity.xml.health.route.TrackSegment
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.time.ExperimentalTime

@EnableMocks
@ExperimentalTime
class HealthDataRepositoryTest {

    @MockK private lateinit var healthDataRepository: AppleHealthDataRepository
    @MockK private lateinit var gpsDataRepository: GlobalPositioningDataRepository
    @MockK private lateinit var converter: WorkoutConversionService

    private lateinit var repository: HealthDataRepository

    @BeforeEach
    internal fun setUp() {
        repository = HealthDataRepository(healthDataRepository, gpsDataRepository, converter)
        every { healthDataRepository.read() } returns AppleHealthData()
        every { gpsDataRepository.read("Route 2020-08-01 7:53pm") } returns getGlobalPositioningData()
        every { converter.convert(AppleHealthData(), HealthRecord::class.java) } returns HealthRecord(emptyList())
        every { converter.convert(getGlobalPositioningData(), ExerciseRoute::class.java)} returns getExerciseRoute()
    }

    @Nested
    inner class GetHealthRecord {
        @Test
        fun `Should call Health Data Repository`() {
            repository.getHealthRecord()
            verify { (healthDataRepository).read() }
        }

        @Test
        fun `Should convert reader response with Spring converter`() {
            repository.getHealthRecord()
            verify { converter.convert(AppleHealthData(), HealthRecord::class.java) }
        }
    }

    @Nested
    inner class GetExerciseRoute {
        @Test
        fun `Should call Global Positioning Data Repository`() {
            repository.getExerciseRoute("Route 2020-08-01 7:53pm")
            verify { (gpsDataRepository).read("Route 2020-08-01 7:53pm") }
        }

        @Test
        fun `Should convert reader response with Spring converter`() {
            repository.getExerciseRoute("Route 2020-08-01 7:53pm")
            verify { converter.convert(getGlobalPositioningData(), ExerciseRoute::class.java) }
        }
    }

    private fun getExerciseRoute(): ExerciseRoute {
        return ExerciseRoute(
            Route(mutableListOf(RoutePoint(1.0, 2.0))),
            LocalDateTime.of(2021,2,1,4,0,0),
            HealthDataSource("Apple Watch", "4")
        )
    }

    private fun getGlobalPositioningData(): GlobalPositioningData {
        return GlobalPositioningData(
            "4", "Apple Watch",
            MetaData("2020-08-23T17:40:02Z"),
            Track("Route 2020-08-01 7:53pm",
                TrackSegment(emptyList())
            )
        )
    }
}