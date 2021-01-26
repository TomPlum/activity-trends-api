package com.github.tomplum.activity.reader

import com.github.tomplum.activity.IntegrationTest
import com.github.tomplum.activity.xml.health.AppleHealthData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
class XMLReaderTest @Autowired constructor(private val reader: XMLReader) {
    @Test
    fun example() {
        val path = "/media/tom/T.Plumpton 2TB Ext HDD/My Documents/Apple Health Exports/export/apple_health_export/export.xml"
        val data = reader.read<AppleHealthData>(path)
        assertThat(data).isNotNull()
    }
}