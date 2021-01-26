package com.github.tomplum.activity.services

import com.github.tomplum.activity.repositories.WorkoutRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExtendWith(MockKExtension::class)
class WorkoutServiceTest {
    @MockK private lateinit var repository: WorkoutRepository

    private lateinit var service: WorkoutService

    @BeforeEach
    internal fun setUp() {
        service = WorkoutService(repository)
    }

    @Test
    fun `Get workout sessions should call repository`() {
        every { repository.read() } returns emptyList()
        service.getWorkoutSessions()
        verify { repository.read() }
    }
}
