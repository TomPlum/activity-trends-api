package com.github.tomplum.activity.xml.health.route

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

@JsonRootName("trkseg")
data class TrackSegment(
    @JacksonXmlProperty(localName = "trkpt")
    val parts: List<TrackPart> = emptyList()
)