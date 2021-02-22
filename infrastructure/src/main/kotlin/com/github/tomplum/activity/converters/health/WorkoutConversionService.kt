package com.github.tomplum.activity.converters.health

import org.springframework.core.convert.support.GenericConversionService
import org.springframework.stereotype.Service
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Service(value = "InfrastructureWorkoutConverter")
class WorkoutConversionService: GenericConversionService() {
    init {
        addConverter(HealthRecordConverter())
        addConverter(GlobalPositioningDataConverter())
    }
}