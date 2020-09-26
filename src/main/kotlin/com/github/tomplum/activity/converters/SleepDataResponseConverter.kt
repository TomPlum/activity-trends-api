package com.github.tomplum.activity.converters

import com.github.tomplum.activity.dto.SleepDataResponse
import com.github.tomplum.activity.dto.SleepSessionResponse
import com.github.tomplum.activity.dto.SleepSnapshotResponse
import com.github.tomplum.activity.sleep.SleepData
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter

@Component
class SleepDataResponseConverter : Converter<SleepData, SleepDataResponse> {
    override fun convert(source: SleepData): SleepDataResponse {
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

        val snapshots = source.snapshots.map { snapshot ->
            val sessions = snapshot.data.map { session ->
                SleepSessionResponse(
                        session.startDate.format(formatter),
                        session.endDate.format(formatter),
                        session.duration,
                        session.isNap,
                        session.sleepQuality,
                        session.awakeTime,
                        session.remSleep,
                        session.lightSleep,
                        session.deepSleep,
                        session.soundsRecorded,
                        session.mood.value
                )
            }

            SleepSnapshotResponse(snapshot.date.format(formatter), sessions)
        }

        return SleepDataResponse(snapshots)
    }

}