package com.github.tomplum.activity.reader

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.tomplum.activity.logging.ActivityTrendsLogger
import com.github.tomplum.activity.xml.health.AppleHealthData
import org.springframework.stereotype.Component
import java.io.FileNotFoundException
import java.io.FileReader
import kotlin.system.measureTimeMillis

@Component
class XMLReader {
    inline fun <reified T : Any> read(fileName: String): T {
        val reader = try {
            FileReader(fileName)
        } catch (e: FileNotFoundException) {
            throw IllegalArgumentException("Cannot find file $fileName", e)
        }
        ActivityTrendsLogger.info("Found $fileName.")
        ActivityTrendsLogger.info("De-serialising into ${T::class.java.simpleName}...")

        val mapper = XmlMapper(JacksonXmlModule().apply { setDefaultUseWrapper(false) }).registerKotlinModule()
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        return doAndLogTime { mapper.readValue(reader) }
    }
}

inline fun <T> doAndLogTime(action: () -> T): T {
    val startTime = System.currentTimeMillis()
    val result: T = action.invoke()
    val endTime = System.currentTimeMillis()
    ActivityTrendsLogger.info("Action took ${endTime - startTime}ms")
    return result
}