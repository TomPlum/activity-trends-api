package com.github.tomplum.activity.reader

import org.apache.commons.csv.CSVFormat
import org.springframework.stereotype.Component
import java.io.InputStreamReader
import java.lang.IllegalArgumentException

@Component
class CSVReader {
    fun read(fileName: String, format: CSVFormat = CSVFormat.DEFAULT): List<MutableMap<String, String?>> = format
            .withFirstRecordAsHeader()
            .withTrailingDelimiter()
            .withIgnoreSurroundingSpaces()
            .withIgnoreEmptyLines()
            .parse(getFileReader(fileName))
            .records.map { it.toMap() }

    private fun getFileReader(name: String): InputStreamReader {
        val resource = CSVReader::class.java.getResource("/csv/$name.csv")
                ?: throw IllegalArgumentException("Cannot find file $name")
        return resource.openStream().reader()
    }
}