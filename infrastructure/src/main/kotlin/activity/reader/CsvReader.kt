package activity.reader

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVRecord

class CsvReader {
    fun read(fileName: String, format: CSVFormat): MutableList<CSVRecord>? {
        val reader = CsvReader::class.java.getResource("/csv/$fileName.csv").openStream().reader()
        return CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader).records
    }
}