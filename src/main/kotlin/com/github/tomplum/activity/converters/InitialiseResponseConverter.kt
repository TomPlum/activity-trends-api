package com.github.tomplum.activity.converters

import com.github.tomplum.activity.data.activity.InitialiseResponse
import com.github.tomplum.activity.data.activity.SnapshotDatesResponse
import com.github.tomplum.activity.sleep.SnapshotDates
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter

@Component
class InitialiseResponseConverter : Converter<SnapshotDates, InitialiseResponse> {
    override fun convert(source: SnapshotDates) = InitialiseResponse(
            SnapshotDatesResponse(source.values.map { it.format(DateTimeFormatter.ISO_LOCAL_DATE) })
    )
}