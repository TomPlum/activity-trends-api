package activity.converters

import activity.sleep.Mood
import activity.sleep.SleepSession
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SleepDataConverter {
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss +0000")

    fun convert(csv: List<Map<String, String>>) = csv.map {
        SleepSession(
                LocalDateTime.parse(it["Start Time"], dateTimeFormatter),
                LocalDateTime.parse(it["End Time"], dateTimeFormatter),
                it["Duration (mins)"]?.toInt() ?: 0,
                it["Nap"] == "YES",
                it["Sleep Quality (%)"]?.toInt() ?: 0,
                it["Time Awake (mins)"]?.toInt() ?: 0,
                it["Time in REM Slep (mins)"]?.toInt() ?: 0,
                it["Time in Light Sleep (mins)"]?.toInt() ?: 0,
                it["Time In Deep Sleep (mins)"]?.toInt() ?: 0,
                it["Sounds Recorded"]?.toInt() ?: 0,
                Mood.fromString(it["Wake-up mood"])
        )
    }
}