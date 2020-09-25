package com.github.tomplum.activity.controllers

import com.github.tomplum.activity.converters.SleepDataConverter
import com.github.tomplum.activity.dto.SleepDataResponse
import com.github.tomplum.activity.services.SleepService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sleep")
class SleepController(private val service: SleepService, private val converter: SleepDataConverter) {

    @GetMapping("/initialise")
    fun initialise(): ResponseEntity<SleepDataResponse> {
        val data = service.getSleepData()
        val response = converter.convert(data)
        return ResponseEntity.ok(response)
    }
}