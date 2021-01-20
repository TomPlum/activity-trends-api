package com.github.tomplum.activity.dto.health

import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("ExportDate")
data class ExportDate(var value: String?)