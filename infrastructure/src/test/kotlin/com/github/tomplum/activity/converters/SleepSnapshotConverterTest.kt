package com.github.tomplum.activity.converters

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.tomplum.activity.document.SleepData
import com.github.tomplum.activity.document.SleepSessionData
import com.github.tomplum.activity.sleep.Mood
import com.github.tomplum.activity.sleep.SleepSession
import com.github.tomplum.activity.sleep.SleepSnapshot
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class SleepSnapshotConverterTest {

    private val converter = SleepSnapshotConverter()

    @Test
    fun convert() {
        val source = getSnapshotData()
        val target = converter.convert(listOf(source))
        assertThat(target).isEqualTo(listOf(getExpectedTarget()))
    }

    private fun getSnapshotData(): SleepSnapshot {
        val session = SleepSession(LocalDateTime.of(2020,9,29,22,30,0), LocalDateTime.of(2020,9,30,7,30,0),
        9, false, 92, 50, 220, 150, 300, 12, Mood.GREAT)
        return SleepSnapshot(LocalDateTime.of(2020, 9, 28, 20, 0, 0), listOf(session))
    }

    private fun getExpectedTarget(): SleepData {
        val session = SleepSessionData("2020-09-29T22:30:00", "2020-09-30T07:30:00", 9, false, 92, 50, 220, 150, 300, 12, "Great")
        return SleepData("2020-09-28T20:00:00", listOf(session))
    }
}