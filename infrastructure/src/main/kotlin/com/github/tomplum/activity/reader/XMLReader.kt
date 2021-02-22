package com.github.tomplum.activity.reader

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.tomplum.activity.exception.InvalidXML
import com.github.tomplum.activity.logging.ActivityTrendsLogger
import com.github.tomplum.activity.xml.XML
import org.springframework.stereotype.Component
import java.io.FileNotFoundException
import java.io.FileReader

@Component
class XMLReader {
    inline fun <reified T : XML> read(fileName: String): T {
        val reader = try {
            FileReader(fileName)
        } catch (e: FileNotFoundException) {
            throw IllegalArgumentException("Cannot find file $fileName", e)
        }

        val targetClassName = T::class.java.simpleName
        ActivityTrendsLogger.info("Found $fileName.")
        ActivityTrendsLogger.info("De-serialising into $targetClassName...")

        val mapper = XmlMapper(JacksonXmlModule().apply { setDefaultUseWrapper(false) }).registerKotlinModule()
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        return doAndLogTime {
            try {
                mapper.readValue(reader)
            } catch (e: MismatchedInputException) {
                throw InvalidXML("$fileName cannot be de-serialised into $targetClassName", e)
            }
        }
    }
}

fun <T> doAndLogTime(action: () -> T): T {
    val startTime = System.currentTimeMillis()
    val result: T = action.invoke()
    val endTime = System.currentTimeMillis()
    ActivityTrendsLogger.info("Action took ${endTime - startTime}ms")
    return result
}