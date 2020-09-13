package activity.converters

import activity.DataFactory
import activity.sleep.Mood
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime


class SleepDataConverterTest {
    private val converter = SleepDataConverter()

    @Nested
    inner class StartTime {
        @Test
        fun valid() {
            val csv = DataFactory.sleepDataCsvResponse()
            val response = converter.convert(csv)
            assertThat(response[0].startDate).isEqualTo(LocalDateTime.of(2018, 8, 21, 21, 44, 3))
        }
    }

    @Nested
    inner class EndTime {
        @Test
        fun valid() {
            val csv = DataFactory.sleepDataCsvResponse()
            val response = converter.convert(csv)
            assertThat(response[0].endDate).isEqualTo(LocalDateTime.of(2018, 8, 22, 6, 0, 56))
        }
    }

    @Nested
    inner class Duration {
        @Test
        fun valid() {
            val csv = DataFactory.sleepDataCsvResponse()
            val response = converter.convert(csv)
            assertThat(response[0].duration).isEqualTo(497)
        }
    }

    @Nested
    inner class Nap {
        @Test
        fun valid() {
            val csv = DataFactory.sleepDataCsvResponse()
            val response = converter.convert(csv)
            assertThat(response[0].isNap).isFalse()
        }
    }

    @Nested
    inner class SleepQuality {
        @Test
        fun valid() {
            val csv = DataFactory.sleepDataCsvResponse()
            val response = converter.convert(csv)
            assertThat(response[0].sleepQuality).isEqualTo(60)
        }
    }

    @Nested
    inner class AwakeTime {
        @Test
        fun valid() {
            val csv = DataFactory.sleepDataCsvResponse()
            val response = converter.convert(csv)
            assertThat(response[0].awakeTime).isEqualTo(127)
        }
    }

    @Nested
    inner class RapidEyeMovementSleepTime {
        @Test
        fun valid() {
            val csv = DataFactory.sleepDataCsvResponse()
            val response = converter.convert(csv)
            assertThat(response[0].remSleep).isEqualTo(70)
        }
    }

    @Nested
    inner class LightSleepTime {
        @Test
        fun valid() {
            val csv = DataFactory.sleepDataCsvResponse()
            val response = converter.convert(csv)
            assertThat(response[0].lightSleep).isEqualTo(150)
        }
    }

    @Nested
    inner class DeepSleepTime {
        @Test
        fun valid() {
            val csv = DataFactory.sleepDataCsvResponse()
            val response = converter.convert(csv)
            assertThat(response[0].deepSleep).isEqualTo(150)
        }
    }

    @Nested
    inner class SoundsRecorded {
        @Test
        fun valid() {
            val csv = DataFactory.sleepDataCsvResponse()
            val response = converter.convert(csv)
            assertThat(response[0].soundsRecorded).isEqualTo(11)
        }
    }

    @Nested
    inner class WakeUpMood {
        @Test
        fun valid() {
            val csv = DataFactory.sleepDataCsvResponse()
            val response = converter.convert(csv)
            assertThat(response[0].mood).isEqualTo(Mood.GOOD)
        }
    }

}