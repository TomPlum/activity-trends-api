package com.github.tomplum.activity.xml.health.route

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

@JsonRootName("metadata")
data class Metadata(
    @JacksonXmlProperty(localName = "time")
    var time: String?
)