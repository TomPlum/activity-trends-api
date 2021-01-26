package com.github.tomplum.activity.converters.sleep

import com.github.tomplum.activity.reader.CSVReader
import com.github.tomplum.activity.reader.headers.SleepDataHeaders.*
import com.github.tomplum.activity.sleep.Mood
import com.github.tomplum.activity.sleep.SleepSession
import com.github.tomplum.activity.sleep.SleepSnapshot
import com.github.tomplum.activity.sleep.SleepTime
import com.github.tomplum.activity.toMood
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

/**
 * Converts the response of the [CSVReader] into a [SleepSnapshot] Domain Object.
 */
@Component
class SleepDataConverter : Converter<List<MutableMap<String, String?>>, SleepSnapshot> {

    override fun convert(source: List<MutableMap<String, String?>>): SleepSnapshot {
        val sessions = source.filter { it[DURATION.header]?.toInt() != 0 }.map {
            SleepSession(
                    it[START_TIME.header]?.toDate() ?: throw IllegalArgumentException("Start Time NULL"),
                    it[END_TIME.header]?.toDate() ?: throw IllegalArgumentException("End Time NULL"),
                    it[DURATION.header]?.toInt() ?: 0,
                    it[NAP.header] == "YES",
                    it[SLEEP_QUALITY.header]?.toInt() ?: 0,
                    SleepTime(
                        it[TIME_AWAKE.header]?.toInt() ?: 0,
                        it[LIGHT_SLEEP.header]?.toInt() ?: 0,
                        it[DEEP_SLEEP.header]?.toInt() ?: 0,
                        it[REM_SLEEP.header]?.toInt() ?: 0,
                    ),
                    it[SOUNDS_RECORDED.header]?.toInt() ?: 0,
                    it[WAKE_UP_MOOD.header]?.toMood() ?: Mood.UNKNOWN
            )
        }
        return SleepSnapshot(LocalDate.now(), sessions)
    }

    private fun String.toDate(): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss +0000")
        try {
            return LocalDateTime.parse(this, formatter)
        } catch (e: DateTimeParseException) {
            val value = if (this.isEmpty()) "EMPTY" else this
            throw IllegalArgumentException("Invalid Date/Time ($value)")
        }
    }

}