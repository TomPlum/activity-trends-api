package com.github.tomplum.activity.services

import com.github.tomplum.activity.repositories.HealthDataRepository
import com.github.tomplum.activity.workout.HealthRecord
import org.springframework.stereotype.Service
import kotlin.time.ExperimentalTime

@Service
@ExperimentalTime
class HealthService(private val repository: HealthDataRepository) {
    fun getHealthRecord(): HealthRecord {
        return repository.getHealthRecord()
    }
}