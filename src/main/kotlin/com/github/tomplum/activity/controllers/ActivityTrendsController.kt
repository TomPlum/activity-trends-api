package com.github.tomplum.activity.controllers

import com.github.tomplum.activity.converters.service.ActivityConversionService
import com.github.tomplum.activity.converters.InitialiseResponseConverter
import com.github.tomplum.activity.services.ActivityService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ActivityTrendsController(private val service: ActivityService, private val converter: ActivityConversionService) {
    @GetMapping("/initialise")
    fun initialise(): ResponseEntity<InitialiseResponseConverter> {
        val dates = service.getSnapshotDates()
        val response = converter.convert(dates, InitialiseResponseConverter::class.java)
        return ResponseEntity.ok(response)
    }
}