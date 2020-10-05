package com.github.tomplum.activity.converters.service

import com.github.tomplum.activity.converters.SleepInitialiseResponseConverter
import com.github.tomplum.activity.converters.SleepSnapshotResponseConverter
import org.springframework.core.convert.support.GenericConversionService
import org.springframework.stereotype.Service

@Service
class SleepConversionService : GenericConversionService() {
    init {
        addConverter(SleepInitialiseResponseConverter())
        addConverter(SleepSnapshotResponseConverter())
    }
}