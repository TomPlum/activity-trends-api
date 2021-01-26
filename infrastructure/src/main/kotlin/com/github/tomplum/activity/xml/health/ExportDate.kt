package com.github.tomplum.activity.xml.health

import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("ExportDate")
data class ExportDate(var value: String?)