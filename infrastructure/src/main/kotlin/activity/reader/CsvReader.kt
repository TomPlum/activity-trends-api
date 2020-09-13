package activity.reader

import org.apache.commons.csv.CSVFormat

class CsvReader {
    fun read(fileName: String, format: CSVFormat): List<MutableMap<String, String>> {
        val reader = CsvReader::class.java.getResource("/csv/$fileName.csv").openStream().reader()
        return format.withFirstRecordAsHeader().parse(reader).records.map { it.toMap() }
    }
}