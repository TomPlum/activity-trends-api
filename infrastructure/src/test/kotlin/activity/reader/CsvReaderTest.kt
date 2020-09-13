package activity.reader

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.apache.commons.csv.CSVFormat
import org.junit.jupiter.api.Test

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
}