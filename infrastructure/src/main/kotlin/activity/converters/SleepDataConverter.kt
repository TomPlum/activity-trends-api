package activity.converters

import activity.reader.headers.SleepDataHeaders.*
import activity.sleep.Mood
import activity.sleep.SleepSession
import activity.toMood
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class SleepDataConverter {


    fun convert(csv: List<MutableMap<String, String?>>) = csv.map {
        SleepSession(
                it[START_TIME.header]?.toDate() ?: throw IllegalArgumentException("Start Time NULL"),
                it[END_TIME.header]?.toDate()  ?: throw IllegalArgumentException("End Time NULL"),
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