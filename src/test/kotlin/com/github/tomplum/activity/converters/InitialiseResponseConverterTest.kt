package com.github.tomplum.activity.converters

import com.github.tomplum.activity.sleep.Mood
import com.github.tomplum.activity.sleep.SleepSession
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.tomplum.activity.dto.SleepDataResponse
import com.github.tomplum.activity.dto.SleepSessionResponse
import com.github.tomplum.activity.dto.SleepSnapshotResponse
import com.github.tomplum.activity.sleep.SleepData
import com.github.tomplum.activity.sleep.SleepSnapshot
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

class InitialiseResponseConverterTest {
    private val converter = InitialiseResponseConverter()

    @Test
    fun convert() {
        val source = getSource()
        val target = converter.convert(source)
        assertThat(target).isEqualTo(getExpected())
    }

    private fun getSource(): SleepData {
        val startDate = LocalDateTime.of(2020, 9, 22, 22, 30, 0)
        val endDate = LocalDateTime.of(2020, 9, 23, 7, 31, 0)
        val session = SleepSession(startDate, endDate, 8, false, 78, 1, 4, 2, 1, 12, Mood.GOOD)
        return SleepData(listOf(SleepSnapshot(LocalDate.of(2020, 9, 20), listOf(session))))
    }

    private fun getExpected(): SleepDataResponse {
        val snapshotData = SleepSessionResponse("2020-09-22T22:30:00", "2020-09-23T07:31:00", 8, false, 78, 1, 4, 2, 1, 12, "Good")
        val snapshot = SleepSnapshotResponse("2020-09-20", listOf(snapshotData))
        return SleepDataResponse(listOf(snapshot))
    }
}