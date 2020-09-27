package com.github.tomplum.activity

import com.github.tomplum.activity.converters.SleepDataConverter
import com.github.tomplum.activity.reader.CsvReader
import com.github.tomplum.activity.repositories.SleepDataRepository
import org.junit.jupiter.api.Test

@IntegrationTest
class MongoETL(val repository: SleepDataRepository, val reader: CsvReader, val converter: SleepDataConverter) {
    @Test
    fun upload() {
        val csv = reader.read("sleep")
        val data = converter.convert(csv)
        repository.insert()
    }
}