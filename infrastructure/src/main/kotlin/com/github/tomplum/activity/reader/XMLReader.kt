package com.github.tomplum.activity.reader

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.tomplum.activity.logging.ActivityTrendsLogger
import org.springframework.stereotype.Component
import java.io.FileNotFoundException
import java.io.FileReader

@Component
class XMLReader {
    inline fun <reified T : Any> read(fileName: String): T {
        val reader = try {
            FileReader(fileName)
        } catch (e: FileNotFoundException) {
            throw IllegalArgumentException("Cannot find file $fileName", e)
        }
        ActivityTrendsLogger.info("Found $fileName")

        val mapper = XmlMapper(JacksonXmlModule().apply { setDefaultUseWrapper(false) }).registerKotlinModule()
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        return mapper.readValue(reader)
    }
}