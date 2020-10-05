package com.github.tomplum.activity.services

import com.github.tomplum.activity.sleep.SnapshotDates
import org.springframework.stereotype.Service

@Service
class ActivityService(private val sleepService: SleepService) {
    fun getSnapshotDates(): SnapshotDates = sleepService.getSleepData().getDates()
}