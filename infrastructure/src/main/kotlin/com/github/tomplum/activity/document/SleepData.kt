package com.github.tomplum.activity.document

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "sleep-data")
data class SleepData(val uploadDate: String, val sessions: List<SleepSessionData>)