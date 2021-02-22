package com.github.tomplum.activity.repositories

import com.github.tomplum.activity.annotations.EnableMocks
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

@EnableMocks
class AppleHealthDataRepositoryTest {
    private lateinit var repository: AppleHealthDataRepository

    @RelaxedMockK private lateinit var reader: XMLReader
    @MockK private lateinit var config: HealthDataConfig

    @BeforeEach
    internal fun setUp() {
        repository = AppleHealthDataRepository(reader, config)
        every { config.exportPath } returns "/example/directory/"
        every { config.fileName } returns "test-export.xml"
    }

    @Nested
    inner class Read {
        @Test
        @Disabled("We can't mock the XML reader because its inline :(")
        fun `Valid property config should call XML reader with correct path`() {
            repository.read()
            verify { reader.read<AppleHealthData>("/example/directory/test-export.xml") }
        }
    }
}