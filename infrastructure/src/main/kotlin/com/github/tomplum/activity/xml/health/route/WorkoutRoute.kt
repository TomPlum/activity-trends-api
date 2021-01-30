package com.github.tomplum.activity.xml.health.route

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

@JsonRootName("gpx")
data class WorkoutRoute(
    @JacksonXmlProperty(isAttribute = true, localName = "version")
    var version: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "creator")
    var creator: String?,

    @JacksonXmlProperty(localName = "metadata")
    var metadata: Metadata?,


)