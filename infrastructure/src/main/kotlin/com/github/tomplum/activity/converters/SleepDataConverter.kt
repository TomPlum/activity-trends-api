package com.github.tomplum.activity.converters

import com.github.tomplum.activity.reader.headers.SleepDataHeaders
import com.github.tomplum.activity.sleep.Mood
import com.github.tomplum.activity.sleep.SleepSession
import com.github.tomplum.activity.toMood
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@Component
class SleepDataConverter : Converter<List<MutableMap<String, String?>>, List<SleepSession>> {

    override fun convert(source: List<MutableMap<String, String?>>): List<SleepSession> = source.map {
        SleepSession(
                it[SleepDataHeaders.START_TIME.header]?.toDate() ?: throw IllegalArgumentException("Start Time NULL"),
                it[SleepDataHeaders.END_TIME.header]?.toDate() ?: throw IllegalArgumentException("End Time NULL"),
                it[SleepDataHeaders.DURATION.header]?.toInt() ?: 0,
                it[SleepDataHeaders.NAP.header] == "YES",
                it[SleepDataHeaders.SLEEP_QUALITY.header]?.toInt() ?: 0,
                it[SleepDataHeaders.TIME_AWAKE.header]?.toInt() ?: 0,
                it[SleepDataHeaders.REM_SLEEP.header]?.toInt() ?: 0,
                it[SleepDataHeaders.LIGHT_SLEEP.header]?.toInt() ?: 0,
                it[SleepDataHeaders.DEEP_SLEEP.header]?.toInt() ?: 0,
                it[SleepDataHeaders.SOUNDS_RECORDED.header]?.toInt() ?: 0,
                it[SleepDataHeaders.WAKE_UP_MOOD.header]?.toMood() ?: Mood.UNKNOWN
        )
    }

    private fun String.toDate(): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss +0000")
        try {
            return LocalDateTime.parse(this, formatter);
        } catch (e: DateTimeParseException) {
            val value = if (this.isEmpty()) "EMPTY" else this;
            throw IllegalArgumentException("Invalid Date/Time ($value)")
        }
    }

}