package com.github.tomplum.activity.repositories

import com.github.tomplum.activity.config.HealthDataConfig
import com.github.tomplum.activity.reader.XMLReader
import com.github.tomplum.activity.xml.health.AppleHealthData
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class GlobalPositioningDataRepositoryTest {
    private lateinit var repository: GlobalPositioningDataRepository

    @RelaxedMockK private lateinit var reader: XMLReader
    @MockK private lateinit var config: HealthDataConfig

    @BeforeEach
    internal fun setUp() {
        repository = GlobalPositioningDataRepository(reader, config)
        every { config.exportPath } returns "/example/directory/"
        every { config.workoutRoutePath } returns "workout-routes/"
    }

    @Nested
    inner class Read {
        @Test
        @Disabled("We can't mock the XML reader because its inline :(")
        fun `Valid property config should call XML reader with correct path`() {
            repository.read("route_2020-08-23_5.59pm")
            verify { reader.read<AppleHealthData>("/example/directory/workout-routes/route_2020-08-23_5.59pm.gpx") }
        }
    }
}