package com.github.tomplum.activity.converters.sleep

import com.github.tomplum.activity.document.SleepData
import com.github.tomplum.activity.document.SleepSessionData
import com.github.tomplum.activity.document.SleepSessionTime
import com.github.tomplum.activity.sleep.SleepSnapshot
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter

/**
 * Converts a List of [SleepSnapshot] Domain Objects into a List of [SleepData] Document Objects
 * to be serialised in a MongoDB.
 */
@Component
class SleepSnapshotConverter : Converter<List<SleepSnapshot>, List<SleepData>> {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    override fun convert(source: List<SleepSnapshot>): List<SleepData> = source.map { snapshot ->
        SleepData(
            snapshot.date.format(DateTimeFormatter.ISO_LOCAL_DATE),
            snapshot.data.map { session ->
                val time = session.time

                SleepSessionData(
                        session.startDate.format(formatter),
                        session.endDate.format(formatter),
                        session.duration,
                        session.isNap,
                        session.sleepQuality,
                        SleepSessionTime(time.awake, time.light, time.deep, time.rem),
                        session.soundsRecorded,
                        session.mood.value
                )
            }
        )
    }
}