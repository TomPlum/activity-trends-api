package com.github.tomplum.activity.exception

data class InvalidXML(override val message: String, override val cause: Exception) : Exception(message, cause)