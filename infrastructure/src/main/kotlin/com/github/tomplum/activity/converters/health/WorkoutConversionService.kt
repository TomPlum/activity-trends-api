package com.github.tomplum.activity.converters.health

import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.support.GenericConversionService
import org.springframework.stereotype.Service
import kotlin.time.ExperimentalTime

@Service
class WorkoutConversionService: GenericConversionService() {
    @ExperimentalTime
    override fun addConverter(converter: Converter<*, *>) {
        addConverter(HealthRecordConverter())
        addConverter(GlobalPositioningDataConverter())
    }
}