package com.github.tomplum.activity.xml.health

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "HealthData")
data class AppleHealthData(
    @JacksonXmlProperty(localName = "ExportDate")
    var exportDate: ExportDate? = null,

    @JacksonXmlProperty(localName = "Me")
    var personalData: PersonalData? = null,

    @JacksonXmlProperty(localName = "Record")
    var records: List<Record> = emptyList(),

    @JacksonXmlProperty(localName = "Workout")
    var workouts: MutableList<Workout> = mutableListOf(),

    @JacksonXmlProperty(localName = "ActivitySummary")
    var activitySummaries: List<ActivitySummary> = emptyList()
)