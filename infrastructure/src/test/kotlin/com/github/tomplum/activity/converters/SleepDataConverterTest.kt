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

class SleepDataConverterTest {

    private val converter = SleepDataConverter()

    @Test
    fun convert() {
        val source = getMongoDocumentResponse()
        val target = converter.convert(listOf(source))
        assertThat(target).isEqualTo(listOf(getExpectedSleepSnapshot()))
    }

    private fun getMongoDocumentResponse(): SleepData {
        val uploadDate = "2020-09-25T21:48"
        val session = SleepSessionData("2018-08-16T22:16:57", "2018-08-17T05:36:42", 440, false, 56, 90, 50, 170, 130, 65, "Ok")
        return SleepData(uploadDate, listOf(session))
    }

    private fun getExpectedSleepSnapshot(): SleepSnapshot {
        val date = LocalDateTime.of(2020, 9, 25, 21, 48)
        val data = SleepSession(
                LocalDateTime.of(2018, 8, 16, 22, 16, 57), LocalDateTime.of(2018, 8, 17, 5, 36, 42),
                440, false, 56, 90, 50, 170, 130, 65, Mood.OK
        )
        return SleepSnapshot(date, listOf(data))
    }
}