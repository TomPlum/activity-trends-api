package com.github.tomplum.activity.controllers

import com.github.tomplum.activity.converters.SleepDataConverter
import com.github.tomplum.activity.services.SleepService
import com.github.tomplum.activity.dto.SleepDataResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sleep")
class SleepController(private val service: SleepService, private val converter: SleepDataConverter) {

    @GetMapping("/initialise")
    fun initialise(): SleepDataResponse {
        val sleepData = service.getSleepData()
        return converter.convert(sleepData) ?: throw IllegalStateException("Response cannot be null")
    }
}