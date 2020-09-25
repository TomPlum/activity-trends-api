package com.github.tomplum.activity.repositories

import com.github.tomplum.activity.document.SleepData
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SleepDataRepository : MongoRepository<SleepData, String> {
    fun getData(): List<SleepData> = findAll()

    fun uploadData(data: SleepData) = insert(data)
}