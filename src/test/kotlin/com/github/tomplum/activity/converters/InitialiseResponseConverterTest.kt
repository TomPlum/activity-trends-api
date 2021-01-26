package com.github.tomplum.activity.converters

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.tomplum.activity.xml.activity.InitialiseResponse
import com.github.tomplum.activity.xml.activity.SnapshotDatesResponse
import com.github.tomplum.activity.sleep.SnapshotDates
import org.junit.jupiter.api.Test
import java.time.LocalDate

class InitialiseResponseConverterTest {

    private val converter = InitialiseResponseConverter()

    @Test
    fun convert() {
        val source = getSource()
        val target = converter.convert(source)
        assertThat(target).isEqualTo(getExpectedTarget())
    }

    private fun getSource() = SnapshotDates(listOf(LocalDate.of(2020,10,5), LocalDate.of(2020,12,15), LocalDate.of(2021,1,1)))

    private fun getExpectedTarget(): InitialiseResponse {
        return InitialiseResponse(SnapshotDatesResponse(listOf("2020-10-05", "2020-12-15", "2021-01-01")))
    }
}