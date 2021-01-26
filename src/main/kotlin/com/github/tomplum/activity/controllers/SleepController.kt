package com.github.tomplum.activity.controllers

import com.github.tomplum.activity.converters.service.SleepConversionService
import com.github.tomplum.activity.xml.sleep.SleepInitialiseResponse
import com.github.tomplum.activity.xml.sleep.SleepSnapshotResponse
import com.github.tomplum.activity.services.SleepService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/sleep")
class SleepController(private val service: SleepService, private val converter: SleepConversionService) {

    @GetMapping("/initialise")
    fun initialise(): ResponseEntity<SleepInitialiseResponse> {
        val data = service.getSleepData()
        val response = converter.convert(data, SleepInitialiseResponse::class.java)!!
        return ResponseEntity.ok(response)
    }

    @GetMapping("/snapshot/{date}")
    fun getSnapshot(@PathVariable date: String): ResponseEntity<SleepSnapshotResponse> {
        val data = service.getSnapshot(LocalDate.parse(date))
        val response = converter.convert(data, SleepSnapshotResponse::class.java)!!
        return ResponseEntity.ok(response)
    }
}