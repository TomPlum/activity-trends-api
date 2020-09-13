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
        assertThat(result?.get(0)?.get("Forename")).isEqualTo("Thomas")
    }

    @Test
    fun `Header value contains a space`() {
        val result = reader.read("test", CSVFormat.DEFAULT)
        assertThat(result?.get(0)?.get("Date of Birth")).isEqualTo("1997-03-02")
    }
}