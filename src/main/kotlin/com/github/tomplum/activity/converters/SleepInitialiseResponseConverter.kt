package com.github.tomplum.activity.converters

import com.github.tomplum.activity.xml.sleep.SleepInitialiseResponse
import com.github.tomplum.activity.xml.sleep.SleepSessionResponse
import com.github.tomplum.activity.xml.sleep.SleepSnapshotResponse
import com.github.tomplum.activity.xml.sleep.SleepTimeResponse
import com.github.tomplum.activity.sleep.SleepData
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter

@Component
class SleepInitialiseResponseConverter : Converter<SleepData, SleepInitialiseResponse> {
    override fun convert(source: SleepData): SleepInitialiseResponse {
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        val snapshot = source.getMostRecentSnapshot()
        val sessions = snapshot?.data?.map { session ->
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
        } ?: emptyList()

        val uploadDate = snapshot?.date?.format(DateTimeFormatter.ISO_LOCAL_DATE) ?: "Unknown"


        val snapshotDates = source.getDates().values.map { it.toString() }
        return SleepInitialiseResponse(snapshotDates, SleepSnapshotResponse(uploadDate, sessions))
    }

}