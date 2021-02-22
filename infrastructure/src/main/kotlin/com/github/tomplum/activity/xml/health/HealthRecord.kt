package com.github.tomplum.activity.xml.health

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

@JsonRootName("Record")
data class HealthRecord(
    @JacksonXmlProperty(isAttribute = true, localName = "type")
    var type: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "unit")
    var unit: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "value")
    var value: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "sourceName")
    var sourceName: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "sourceVersion")
    var sourceVersion: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "device")
    var device: String? = null,

    @JacksonXmlProperty(isAttribute = true, localName = "creationDate")
    var creationDate: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "startDate")
    var startDate: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "endDate")
    var endDate: String?,

    @JacksonXmlProperty(localName = "MetadataEntry")
    var metadata: List<MetadataEntry>? = emptyList(),

    @JacksonXmlProperty(localName = "HeartRateVariabilityMetadataList")
    var heartRateVariabilityMetadata: HeartRateVariabilityMetadata? = null,
)