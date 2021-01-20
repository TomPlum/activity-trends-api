package com.github.tomplum.activity.dto.health

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

@JsonRootName("HeartRateVariabilityMetadataList")
data class HeartRateVariabilityMetadata(
    @JacksonXmlProperty(localName = "InstantaneousBeatsPerMinute")
    var heartRate: List<InstantaneousBeatsPerMinute> = emptyList(),
)