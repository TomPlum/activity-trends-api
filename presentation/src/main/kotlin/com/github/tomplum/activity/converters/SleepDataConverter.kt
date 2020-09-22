package com.github.tomplum.activity.converters

import com.github.tomplum.activity.dto.SleepDataResponse
import com.github.tomplum.activity.dto.SleepDataSnapshot
import com.github.tomplum.activity.sleep.SleepData
import org.springframework.core.convert.converter.Converter
import java.time.format.DateTimeFormatter

class SleepDataConverter : Converter<SleepData, SleepDataResponse> {
    override fun convert(source: SleepData): SleepDataResponse? {
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

        val snapshots = source.snapshots.map { it.data }.map {
            SleepDataSnapshot(
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

        return SleepDataResponse(snapshots)
    }

}