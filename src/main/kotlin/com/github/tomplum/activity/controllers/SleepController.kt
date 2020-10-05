package com.github.tomplum.activity.controllers

import com.github.tomplum.activity.converters.service.SleepConversionService
import com.github.tomplum.activity.dto.sleep.SleepInitialiseResponse
import com.github.tomplum.activity.dto.sleep.SleepSnapshotResponse
import com.github.tomplum.activity.exceptions.SleepDataNotFound
import com.github.tomplum.activity.exceptions.SnapshotNotFound
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
        val response = converter.convert(data, SleepInitialiseResponse::class.java) ?: throw SleepDataNotFound()
        return ResponseEntity.ok(response)
    }

    @GetMapping("/snapshot/{date}")
    fun getSnapshot(@PathVariable date: String): ResponseEntity<SleepSnapshotResponse> {
        val data = service.getSnapshot(LocalDate.parse(date))
        val response = converter.convert(data, SleepSnapshotResponse::class.java) ?: throw SnapshotNotFound(date)
        return ResponseEntity.ok(response)
    }
}