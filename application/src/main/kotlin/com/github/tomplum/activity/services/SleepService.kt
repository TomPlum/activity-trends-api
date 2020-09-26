package com.github.tomplum.activity.services

import com.github.tomplum.activity.converters.SleepSnapshotConverter
import com.github.tomplum.activity.repositories.SleepDataRepository
import com.github.tomplum.activity.sleep.SleepData
import org.springframework.stereotype.Service

@Service
class SleepService(private val repository: SleepDataRepository, private val converter: SleepSnapshotConverter) {
    fun getSleepData(): SleepData {
        val data = repository.findAll()
        val snapshots = converter.convert(data)
        return SleepData(snapshots)
    }
}