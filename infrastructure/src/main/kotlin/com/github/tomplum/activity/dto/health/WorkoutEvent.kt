package com.github.tomplum.activity.dto.health

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

@JsonRootName("WorkoutEvent")
data class WorkoutEvent(
    @JacksonXmlProperty(isAttribute = true, localName = "type")
    var type: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "date")
    var date: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "duration")
    var duration: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "durationUnit")
    var durationUnit: String?,
)