package com.github.tomplum.activity.converters.sleep

import com.github.tomplum.activity.document.SleepData
import com.github.tomplum.activity.repositories.SleepDataRepository
import com.github.tomplum.activity.sleep.SleepSession
import com.github.tomplum.activity.sleep.SleepSnapshot
import com.github.tomplum.activity.sleep.SleepTime
import com.github.tomplum.activity.toMood
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.LocalDate
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
        val sessions = data.sessions.map { session ->
            val time = session.time
            SleepSession(
                    LocalDateTime.parse(session.startDate, formatter),
                    LocalDateTime.parse(session.endDate, formatter),
                    session.duration,
                    session.isNap,
                    session.sleepQuality,
                    SleepTime(time.awake, time.light, time.deep, time.rem,),
                    session.soundsRecorded,
                    session.mood.toMood()
            )
        }
        val uploadDate = LocalDate.parse(data.uploadDate, DateTimeFormatter.ISO_LOCAL_DATE)
        SleepSnapshot(uploadDate, sessions)
    }
}