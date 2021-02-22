package com.github.tomplum.activity.xml.health.route

import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

@JsonRootName("extensions")
data class Extensions(
    @JacksonXmlProperty(localName = "speed")
    var speed: String?,

    @JacksonXmlProperty(localName = "course")
    var course: String?,

    @JacksonXmlProperty(localName = "hAcc")
    var horizontalAcceleration: String?,

    @JacksonXmlProperty(localName = "vAcc")
    var verticalAcceleration: String?,
)