package com.github.tomplum.activity.converters

import org.springframework.core.convert.support.GenericConversionService
import org.springframework.stereotype.Service

@Service
class SleepConversionService : GenericConversionService() {
    init {
        addConverter(InitialiseResponseConverter())
        addConverter(SnapshotResponseConverter())
    }
}