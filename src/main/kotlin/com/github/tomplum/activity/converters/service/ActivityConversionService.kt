package com.github.tomplum.activity.converters.service

import com.github.tomplum.activity.converters.InitialiseResponseConverter
import org.springframework.core.convert.support.GenericConversionService
import org.springframework.stereotype.Service

@Service
class ActivityConversionService : GenericConversionService() {
    init {
        addConverter(InitialiseResponseConverter())
    }
}