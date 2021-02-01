package com.github.tomplum.activity.services

import com.github.tomplum.activity.repositories.HealthDataRepository
import com.github.tomplum.activity.workout.HealthRecord
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
class HealthServiceTest {
    @MockK private lateinit var repository: HealthDataRepository

    private lateinit var service: HealthService

    @BeforeEach
    internal fun setUp() {
        service = HealthService(repository)
    }

    @Test
    fun `Get health record should call repository`() {
        every { repository.getHealthRecord() } returns HealthRecord(emptyList())
        service.getHealthRecord()
        verify { repository.getHealthRecord() }
    }
}
