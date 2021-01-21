package com.github.tomplum.activity.dto.health

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

@JsonRootName("WorkoutRoot")
data class WorkoutRoute(
    @JacksonXmlProperty(isAttribute = true, localName = "sourceName")
    var sourceName: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "sourceVersion")
    var sourceVersion: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "device")
    var device: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "creationDate")
    var creationDate: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "startDate")
    var startDate: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "endDate")
    var endDate: String?,

    @JacksonXmlProperty(localName = "MetadataEntry")
    var metadata: List<MetadataEntry>? = emptyList(),

    @JacksonXmlProperty(localName = "FileReference")
    var fileReference: FileReference? = null,
)