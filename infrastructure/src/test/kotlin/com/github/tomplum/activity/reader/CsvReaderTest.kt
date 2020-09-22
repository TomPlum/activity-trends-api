package com.github.tomplum.activity.reader

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.apache.commons.csv.CSVFormat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CsvReaderTest {
    private val reader = CsvReader()

    @Test
    fun read() {
        val result = reader.read("test", CSVFormat.DEFAULT)
        assertThat(result[0]["Forename"]).isEqualTo("Thomas")
    }

    @Test
    fun `Header value contains a space`() {
        val result = reader.read("test", CSVFormat.DEFAULT)
        assertThat(result[0]["Date of Birth"]).isEqualTo("1997-03-02")
    }

    @Test
    fun invalidFileName() {
        val e = assertThrows<IllegalArgumentException> { reader.read("invalid-file-name", CSVFormat.DEFAULT) }
        assertThat(e.message).isEqualTo("Cannot find file invalid-file-name")
    }
}