package com.github.tomplum.activity

import com.github.tomplum.activity.converters.SleepDataConverter
import com.github.tomplum.activity.converters.SleepDocumentConverter
import com.github.tomplum.activity.reader.CsvReader
import com.github.tomplum.activity.repositories.SleepDataRepository
import org.junit.jupiter.api.Test

@IntegrationTest
class MongoETL(val repository: SleepDataRepository, val reader: CsvReader,
               val sleepDataConverter: SleepDataConverter, val sleepDocumentConverter: SleepDocumentConverter
) {
    @Test
    fun upload() {
        val csv = reader.read("sleep")
        val snapshot = sleepDataConverter.convert(csv)
        sleepDocumentConverter.convert(snapshot)
        repository.insert()
    }
}