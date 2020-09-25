package com.github.tomplum.activity.repositories

import com.github.tomplum.activity.IntegrationTest
import com.github.tomplum.activity.document.SleepSessionData
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
open class SleepDataRepositoryTest {

    @Autowired
    private lateinit var repository: SleepDataRepository

    @Test
    fun insert() {
        val sleepData = SleepSessionData("2018-08-16 22:16:57", "2018-08-17 05:36:42", 440, false, 56, 90, 50, 170, 130, 65, "Ok")
        repository.insert(sleepData)
    }
}