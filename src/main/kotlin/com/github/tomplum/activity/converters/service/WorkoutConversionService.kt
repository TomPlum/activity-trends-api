package com.github.tomplum.activity.converters.service

import com.github.tomplum.activity.converters.WorkoutRouteResponseConverter
import com.github.tomplum.activity.converters.WorkoutSessionResponseConverter
import org.springframework.core.convert.support.GenericConversionService
import org.springframework.stereotype.Service
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Service(value = "PresentationWorkoutConverter")
class WorkoutConversionService: GenericConversionService() {
    init {
        addConverter(WorkoutSessionResponseConverter())
        addConverter(WorkoutRouteResponseConverter())
    }
}