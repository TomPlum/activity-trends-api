package com.github.tomplum.activity.repositories

import com.github.tomplum.activity.EnableMocks
import com.github.tomplum.activity.converters.health.WorkoutSessionConverter
import com.github.tomplum.activity.dto.health.AppleHealthData
import com.github.tomplum.activity.reader.XMLReader
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.time.ExperimentalTime

@EnableMocks
@ExperimentalTime
class WorkoutRepositoryTest {

    @MockK private lateinit var reader: XMLReader
    @MockK private lateinit var converter: WorkoutSessionConverter

    private lateinit var repository: WorkoutRepository

    @BeforeEach
    internal fun setUp() {
        repository = WorkoutRepository(reader, converter)
        every { reader.read<AppleHealthData>("export.xml") } returns AppleHealthData()
    }

    @Nested
    @Disabled("Not sure if its possible to mock an inline-reified function")
    inner class Read {
        @Test
        fun `Should call the XML reader`() {
            repository.read()
            verify { (reader).read<AppleHealthData>("export.xml") }
        }

        @Test
        fun `Should convert reader response with Spring converter`() {
            repository.read()
            verify { converter.convert(AppleHealthData()) }
        }
    }
}