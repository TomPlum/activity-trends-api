package com.github.tomplum.activity

import com.github.tomplum.activity.converters.SleepDataConverter
import com.github.tomplum.activity.converters.SleepSnapshotConverter
import com.github.tomplum.activity.reader.CSVReader
import com.github.tomplum.activity.repositories.SleepDataRepository
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
class MongoETL @Autowired constructor(
    private val repository: SleepDataRepository,
    private val reader: CSVReader,
    private val sleepDataConverter: SleepDataConverter,
    private val sleepSnapshotConverter: SleepSnapshotConverter
) {
    @Test
    @Disabled
    fun upload() {
        val csv = reader.read("sleep")
        val snapshot = sleepDataConverter.convert(csv)
        val documents = sleepSnapshotConverter.convert(listOf(snapshot))
        repository.insert(documents)
    }
}