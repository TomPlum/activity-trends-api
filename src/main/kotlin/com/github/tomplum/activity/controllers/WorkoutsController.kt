package com.github.tomplum.activity.controllers

import com.github.tomplum.activity.converters.service.WorkoutConversionService
import com.github.tomplum.activity.dto.workouts.WorkoutSessionResponse
import com.github.tomplum.activity.services.WorkoutService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.time.ExperimentalTime

@ExperimentalTime
@RestController
@RequestMapping("/workouts")
class WorkoutsController(private val service: WorkoutService, private val converter: WorkoutConversionService) {
    @GetMapping("/sessions")
    fun getWorkoutSessions(): ResponseEntity<WorkoutSessionResponse> {
        val sessions = service.getWorkoutSessions()
        val response = converter.convert(sessions, WorkoutSessionResponse::class.java)!!
        return ResponseEntity.ok(response)
    }
}