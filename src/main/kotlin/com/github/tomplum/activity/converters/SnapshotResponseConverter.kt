package com.github.tomplum.activity.converters

import com.github.tomplum.activity.dto.SleepSessionResponse
import com.github.tomplum.activity.dto.SleepSnapshotResponse
import com.github.tomplum.activity.sleep.SleepSnapshot
import org.springframework.core.convert.converter.Converter
import java.time.format.DateTimeFormatter

class SnapshotResponseConverter : Converter<SleepSnapshot, SleepSnapshotResponse> {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    override fun convert(source: SleepSnapshot): SleepSnapshotResponse {
        val date = source.date.format(DateTimeFormatter.ISO_LOCAL_DATE)
        val data = source.data.map {
            SleepSessionResponse(
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
        return SleepSnapshotResponse(date, data)
    }
}