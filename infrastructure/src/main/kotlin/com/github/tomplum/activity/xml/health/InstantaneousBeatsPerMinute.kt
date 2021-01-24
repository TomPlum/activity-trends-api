package com.github.tomplum.activity.xml.health

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

@JsonRootName("InstantaneousBeatsPerMinute")
data class InstantaneousBeatsPerMinute(
    @JacksonXmlProperty(isAttribute = true, localName = "bpm")
    var bpm: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "time")
    var time: String?,
)