package activity.converters

import activity.reader.headers.SleepDataHeaders.*
import activity.sleep.Mood
import activity.sleep.SleepSession
import toMood
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SleepDataConverter {
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss +0000")

    fun convert(csv: List<Map<String, String>>) = csv.map {
        SleepSession(
                LocalDateTime.parse(it[START_TIME.header], dateTimeFormatter),
                LocalDateTime.parse(it[END_TIME.header], dateTimeFormatter),
                it[DURATION.header]?.toInt() ?: 0,
                it[NAP.header] == "YES",
                it[SLEEP_QUALITY.header]?.toInt() ?: 0,
                it[TIME_AWAKE.header]?.toInt() ?: 0,
                it[REM_SLEEP.header]?.toInt() ?: 0,
                it[LIGHT_SLEEP.header]?.toInt() ?: 0,
                it[DEEP_SLEEP.header]?.toInt() ?: 0,
                it[SOUNDS_RECORDED.header]?.toInt() ?: 0,
                it[WAKE_UP_MOOD.header]?.toMood() ?: Mood.UNKNOWN
        )
    }
}