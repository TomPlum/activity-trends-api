package com.github.tomplum.activity.converters

import com.github.tomplum.activity.document.SleepData
import com.github.tomplum.activity.document.SleepSessionData
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
            snapshot.data.map {
                SleepSessionData(
                        it.startDate.format(formatter),
                        it.endDate.format(formatter),
                        it.duration,
                        it.isNap,
                        it.sleepQuality,
                        it.awakeTime,
                        it.remSleep,
                        it.lightSleep,
                        it.deepSleep,
                        it.soundsRecorded,
                        it.mood.value
                )
            }
        )
    }
}