package com.github.tomplum.activity.services

import com.github.tomplum.activity.converters.sleep.SleepDocumentConverter
import com.github.tomplum.activity.repositories.SleepDataRepository
import com.github.tomplum.activity.sleep.SleepData
import com.github.tomplum.activity.sleep.SleepSnapshot
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SleepService(private val repository: SleepDataRepository, private val converter: SleepDocumentConverter) {
    fun getSleepData(): SleepData {
        val data = repository.findAll()
        val snapshots = converter.convert(data)
        return SleepData(snapshots)
    }

    fun getSnapshot(date: LocalDate): SleepSnapshot {
        val data = repository.findByUploadDate(date.toString())
        val snapshot = converter.convert(listOf(data))
        return snapshot.first()
    }
}