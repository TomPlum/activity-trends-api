package com.github.tomplum.activity.converters

import com.github.tomplum.activity.DataFactory
import com.github.tomplum.activity.sleep.Mood
import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import com.github.tomplum.activity.converters.sleep.SleepDataConverter
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SleepDataConverterTest {
    private val converter = SleepDataConverter()
    private val csv = DataFactory.sleepDataCsvResponse()

    @Nested
    inner class SnapshotDate {
        @Test
        fun date() {
            val response = converter.convert(csv)
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            assertThat(response.date.format(formatter)).isEqualTo(LocalDate.now().format(formatter))
        }
    }

    @Nested
    inner class StartTime {
        @Test
        fun valid() {
            val response = converter.convert(csv)
            assertThat(response.data[0].startDate).isEqualTo(LocalDateTime.of(2018, 8, 21, 21, 44, 3))
        }

        @Test
        fun nullValue() {
            csv[0]["Start Time"] = null
            val e = assertThrows<IllegalArgumentException> { converter.convert(csv) }
            assertThat(e.message).isEqualTo("Start Time NULL")
        }

        @Test
        fun emptyValue() {
            csv[0]["Start Time"] = ""
            val e = assertThrows<IllegalArgumentException> { converter.convert(csv) }
            assertThat(e.message).isEqualTo("Invalid Date/Time (EMPTY)")
        }

        @ParameterizedTest
        @ValueSource(strings = ["02/03/1997 12:01:00", "15:46:04 1997-03-02"])
        fun wrongFormat(date: String?) {
            csv[0]["Start Time"] = date
            val e = assertThrows<IllegalArgumentException> { converter.convert(csv) }
            assertThat(e.message).isEqualTo("Invalid Date/Time ($date)")
        }
    }

    @Nested
    inner class EndTime {
        @Test
        fun valid() {
            val csv = DataFactory.sleepDataCsvResponse()
            val response = converter.convert(csv)
            assertThat(response.data[0].endDate).isEqualTo(LocalDateTime.of(2018, 8, 22, 6, 0, 56))
        }

        @Test
        fun nullValue() {
            csv[0]["End Time"] = null
            val e = assertThrows<IllegalArgumentException> { converter.convert(csv) }
            assertThat(e.message).isEqualTo("End Time NULL")
        }

        @Test
        fun emptyValue() {
            csv[0]["End Time"] = ""
            val e = assertThrows<IllegalArgumentException> { converter.convert(csv) }
            assertThat(e.message).isEqualTo("Invalid Date/Time (EMPTY)")
        }

        @ParameterizedTest
        @ValueSource(strings = ["02/03/1997 12:01:00", "15:46:04 1997-03-02"])
        fun wrongFormat(date: String?) {
            csv[0]["End Time"] = date
            val e = assertThrows<IllegalArgumentException> { converter.convert(csv) }
            assertThat(e.message).isEqualTo("Invalid Date/Time ($date)")
        }
    }

    @Nested
    inner class Duration {
        @Test
        fun valid() {
            val response = converter.convert(csv)
            assertThat(response.data[0].duration).isEqualTo(497)
        }

        @Test
        fun `0 Duration should be filtered out from the response`() {
            csv[0]["Duration (mins)"] = "0"
            val response = converter.convert(csv)
            assertThat(response.data).isEmpty()
        }
    }

    @Nested
    inner class Nap {
        @Test
        fun valid() {
            val response = converter.convert(csv)
            assertThat(response.data[0].isNap).isFalse()
        }
    }

    @Nested
    inner class SleepQuality {
        @Test
        fun valid() {
            val response = converter.convert(csv)
            assertThat(response.data[0].sleepQuality).isEqualTo(60)
        }
    }

    @Nested
    inner class AwakeTime {
        @Test
        fun valid() {
            val response = converter.convert(csv)
            assertThat(response.data[0].time.awake).isEqualTo(127)
        }
    }

    @Nested
    inner class RapidEyeMovementSleepTime {
        @Test
        fun valid() {
            val response = converter.convert(csv)
            assertThat(response.data[0].time.rem).isEqualTo(70)
        }
    }

    @Nested
    inner class LightSleepTime {
        @Test
        fun valid() {
            val response = converter.convert(csv)
            assertThat(response.data[0].time.light).isEqualTo(150)
        }
    }

    @Nested
    inner class DeepSleepTime {
        @Test
        fun valid() {
            val response = converter.convert(csv)
            assertThat(response.data[0].time.deep).isEqualTo(150)
        }
    }

    @Nested
    inner class SoundsRecorded {
        @Test
        fun valid() {
            val response = converter.convert(csv)
            assertThat(response.data[0].soundsRecorded).isEqualTo(11)
        }
    }

    @Nested
    inner class WakeUpMood {
        @Test
        fun valid() {
            val response = converter.convert(csv)
            assertThat(response.data[0].mood).isEqualTo(Mood.GOOD)
        }
    }
}