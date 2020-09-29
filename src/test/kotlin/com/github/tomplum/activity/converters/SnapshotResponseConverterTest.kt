package com.github.tomplum.activity.converters

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.tomplum.activity.dto.SleepSessionResponse
import com.github.tomplum.activity.dto.SleepSnapshotResponse
import com.github.tomplum.activity.dto.SleepTimeResponse
import com.github.tomplum.activity.sleep.Mood
import com.github.tomplum.activity.sleep.SleepSession
import com.github.tomplum.activity.sleep.SleepSnapshot
import com.github.tomplum.activity.sleep.SleepTime
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

class SnapshotResponseConverterTest {

    private val converter = SnapshotResponseConverter()

    @Test
    fun convert() {
        val source = getSource()
        val target = converter.convert(source)
        assertThat(target).isEqualTo(getExpected())
    }

    private fun getSource(): SleepSnapshot {
        val startDate = LocalDateTime.of(2020, 9, 22, 22, 30, 0)
        val endDate = LocalDateTime.of(2020, 9, 23, 7, 31, 0)
        val time = SleepTime(1, 1, 4, 2)
        val session = SleepSession(startDate, endDate, 8, false, 78, time, 12, Mood.GOOD)
        return SleepSnapshot(LocalDate.of(2020, 9, 20), listOf(session))
    }

    private fun getExpected(): SleepSnapshotResponse {
        val time = SleepTimeResponse(1, 1, 4, 2)
        val snapshotData = SleepSessionResponse("2020-09-22T22:30:00", "2020-09-23T07:31:00", 8, false, 78, time, 12, "Good")
        return SleepSnapshotResponse("2020-09-20", listOf(snapshotData))
    }
}