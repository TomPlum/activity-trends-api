package com.github.tomplum.activity.repositories

import com.github.tomplum.activity.document.SleepData
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SleepRepository : MongoRepository<SleepData, String> {
    fun findByUploadDate(uploadDate: String): SleepData
}