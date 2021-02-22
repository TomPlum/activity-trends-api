package com.github.tomplum.activity.converters

import com.github.tomplum.activity.data.sleep.SleepSessionResponse
import com.github.tomplum.activity.data.sleep.SleepSnapshotResponse
import com.github.tomplum.activity.data.sleep.SleepTimeResponse
import com.github.tomplum.activity.sleep.SleepSnapshot
import org.springframework.core.convert.converter.Converter
import java.time.format.DateTimeFormatter

class SleepSnapshotResponseConverter : Converter<SleepSnapshot, SleepSnapshotResponse> {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    override fun convert(source: SleepSnapshot): SleepSnapshotResponse {
        val date = source.date.format(DateTimeFormatter.ISO_LOCAL_DATE)
        val data = source.data.map { session ->
            val time = session.time
            SleepSessionResponse(
                    session.startDate.format(formatter),
                    session.endDate.format(formatter),
                    session.duration,
                    session.isNap,
                    session.sleepQuality,
                    SleepTimeResponse(time.awake, time.light, time.deep, time.rem),
                    session.soundsRecorded,
                    session.mood.value
            )
        }
        return SleepSnapshotResponse(date, data)
    }
}