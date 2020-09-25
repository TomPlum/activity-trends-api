package com.github.tomplum.activity.services

import com.github.tomplum.activity.repositories.SleepDataRepository
import com.github.tomplum.activity.sleep.SleepData
import org.springframework.stereotype.Service

@Service
class SleepService(private val repository: SleepDataRepository) {
    fun getSleepData(): SleepData {
        return SleepData(listOf())
    }
}