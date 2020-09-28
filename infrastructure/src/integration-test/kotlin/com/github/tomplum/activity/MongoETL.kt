package com.github.tomplum.activity

import com.github.tomplum.activity.converters.SleepDataConverter
import com.github.tomplum.activity.converters.SleepSnapshotConverter
import com.github.tomplum.activity.reader.CsvReader
import com.github.tomplum.activity.repositories.SleepDataRepository
import org.junit.jupiter.api.Test

@IntegrationTest
class MongoETL(val repository: SleepDataRepository, val reader: CsvReader,
               val sleepDataConverter: SleepDataConverter, val sleepSnapshotConverter: SleepSnapshotConverter
) {
    @Test
    fun upload() {
        val csv = reader.read("sleep")
        val data = sleepDataConverter.convert(csv)
        sleepSnapshotConverter.convert()
        repository.insert()
    }
}