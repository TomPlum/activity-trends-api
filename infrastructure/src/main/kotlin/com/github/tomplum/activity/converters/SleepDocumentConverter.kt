package com.github.tomplum.activity.converters

import com.github.tomplum.activity.document.SleepData
import com.github.tomplum.activity.sleep.SleepSession
import com.github.tomplum.activity.sleep.SleepSnapshot
import com.github.tomplum.activity.repositories.SleepDataRepository
import com.github.tomplum.activity.toMood
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Converts a List of [SleepData] Document Objects returned from MongoDB via the [SleepDataRepository] into
 * a List of [SleepSnapshot] Domain Objects.
 */
@Component
class SleepDocumentConverter : Converter<List<SleepData>, List<SleepSnapshot>> {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    override fun convert(source: List<SleepData>): List<SleepSnapshot> = source.map { data ->
        val sessions = data.sessions.map {
            SleepSession(
                    LocalDateTime.parse(it.startDate, formatter),
                    LocalDateTime.parse(it.endDate, formatter),
                    it.duration,
                    it.isNap,
                    it.sleepQuality,
                    it.awakeTime,
                    it.remSleep,
                    it.lightSleep,
                    it.deepSleep,
                    it.soundsRecorded,
                    it.mood.toMood()
            )
        }
        SleepSnapshot(LocalDateTime.parse(data.uploadDate, formatter), sessions)
    }
}