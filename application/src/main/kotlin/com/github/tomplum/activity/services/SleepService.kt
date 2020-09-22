package com.github.tomplum.activity.services

import com.github.tomplum.activity.sleep.SleepData
import com.github.tomplum.activity.sleep.SleepSnapshot

class SleepService {
    fun getSleepData(): SleepData {
        return SleepData(listOf())
    }
}