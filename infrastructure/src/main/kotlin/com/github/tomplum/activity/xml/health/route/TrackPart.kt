package com.github.tomplum.activity.xml.health.route

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

@JsonRootName("trkpt")
data class TrackPart(
    @JacksonXmlProperty(isAttribute = true, localName = "lon")
    var longitude: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "lat")
    var latitude: String?,

    @JacksonXmlProperty(localName = "ele")
    var elevation: String?,

    @JacksonXmlProperty(localName = "time")
    var time: String?,

    @JacksonXmlProperty(localName = "extensions")
    var extensions: Extensions?
)