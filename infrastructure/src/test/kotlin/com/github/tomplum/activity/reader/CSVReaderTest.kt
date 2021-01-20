package com.github.tomplum.activity.reader

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.apache.commons.csv.CSVFormat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CSVReaderTest {
    private val reader = CSVReader()

    @Test
    fun read() {
        val result = reader.read("test")
        assertThat(result[0]["Forename"]).isEqualTo("Thomas")
    }

    @Test
    fun `Header value contains a space`() {
        val result = reader.read("test")
        assertThat(result[0]["Date of Birth"]).isEqualTo("1997-03-02")
    }

    @Test
    fun invalidFileName() {
        val e = assertThrows<IllegalArgumentException> { reader.read("invalid-file-name", CSVFormat.DEFAULT) }
        assertThat(e.message).isEqualTo("Cannot find file invalid-file-name")
    }

    @Test
    fun testSleepData() {
        val result = reader.read("sleep-data")
        assertThat(result[0]).isEqualTo(mapOf(
                Pair("Start Time", "2018-08-16 22:16:57 +0000"),
                Pair("End Time", "2018-08-17 05:36:42 +0000"),
                Pair("Duration (mins)", "440"),
                Pair("Nap", "NO"),
                Pair("Sleep Quality (%)", "56"),
                Pair("Time Awake (mins)", "90"),
                Pair("Time in REM Slep (mins)", "50"),
                Pair("Time in Light Sleep (mins)", "170"),
                Pair("Time In Deep Sleep (mins)", "130"),
                Pair("Sounds Recorded", "65"),
                Pair("Wake-up mood", "Ok"))
        )
    }
}