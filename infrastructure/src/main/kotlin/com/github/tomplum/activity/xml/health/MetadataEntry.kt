package com.github.tomplum.activity.xml.health

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

@JsonRootName("MetadataEntry")
data class MetadataEntry(
    @JacksonXmlProperty(isAttribute = true, localName = "key")
    var key: String?,

    @JacksonXmlProperty(isAttribute = true, localName = "value")
    var value: String?,
)