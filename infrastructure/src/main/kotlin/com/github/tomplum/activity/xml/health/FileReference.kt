package com.github.tomplum.activity.xml.health

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

@JsonRootName("FileReference")
data class FileReference(
    @JacksonXmlProperty(isAttribute = true, localName = "path")
    var path: String?,
)