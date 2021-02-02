package com.github.tomplum.activity.controllers

import com.github.tomplum.activity.converters.service.WorkoutConversionService
import com.github.tomplum.activity.data.workouts.WorkoutRouteResponse
import com.github.tomplum.activity.data.workouts.WorkoutSessionResponse
import com.github.tomplum.activity.services.HealthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.time.ExperimentalTime

@ExperimentalTime
@RestController
@RequestMapping("/workouts")
class WorkoutsController(private val service: HealthService, private val converter: WorkoutConversionService) {
    @GetMapping("/sessions")
    fun getWorkoutSessions(): ResponseEntity<WorkoutSessionResponse> {
        val healthRecord = service.getHealthRecord()
        val response = converter.convert(healthRecord, WorkoutSessionResponse::class.java)!!
        return ResponseEntity.ok(response)
    }

    @GetMapping("/session/{routeName}")
    fun getWorkoutRoute(@PathVariable routeName: String): ResponseEntity<WorkoutRouteResponse> {
        val route = service.getWorkoutExerciseRoute(routeName)
        val response = converter.convert(route, WorkoutRouteResponse::class.java)!!
        return ResponseEntity.ok(response)
    }
}