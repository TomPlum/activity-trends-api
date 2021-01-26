package com.github.tomplum.activity.controllers

import com.github.tomplum.activity.annotations.EnableMocks
import com.github.tomplum.activity.converters.service.WorkoutConversionService
import com.github.tomplum.activity.xml.workouts.WorkoutSessionResponse
import com.github.tomplum.activity.services.WorkoutService
import com.github.tomplum.activity.workout.WorkoutSession
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@EnableMocks
class WorkoutsControllerTest {

    @RelaxedMockK private lateinit var service: WorkoutService
    @RelaxedMockK private lateinit var conversionService: WorkoutConversionService
    @MockK private lateinit var session: WorkoutSession

    private lateinit var controller: WorkoutsController

    @BeforeEach
    internal fun setUp() {
        controller = WorkoutsController(service, conversionService)
        every { service.getWorkoutSessions() } returns mutableListOf(session)
        every {
            conversionService.convert(mutableListOf(session), WorkoutSessionResponse::class.java)
        } returns WorkoutSessionResponse(emptyList())
    }

    @Test
    fun `Get workout sessions should call service method`() {
        controller.getWorkoutSessions()
        verify { service.getWorkoutSessions() }
    }

    @Test
    fun `Get workout sessions should call Spring converter`() {
        controller.getWorkoutSessions()
        verify { conversionService.convert(mutableListOf(session), WorkoutSessionResponse::class.java) }
    }
}