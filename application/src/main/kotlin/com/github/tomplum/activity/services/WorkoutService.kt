package com.github.tomplum.activity.services

import com.github.tomplum.activity.repositories.WorkoutRepository
import com.github.tomplum.activity.workout.WorkoutSession
import org.springframework.stereotype.Service
import kotlin.time.ExperimentalTime

@Service
@ExperimentalTime
class WorkoutService(private val repository: WorkoutRepository) {
    fun getWorkoutSessions(): List<WorkoutSession> {
        return repository.read()
    }
}