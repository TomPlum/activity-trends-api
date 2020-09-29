package com.github.tomplum.activity.document

data class SleepSessionData(
        val startDate: String,
        val endDate: String,
        val duration: Int,
        val isNap: Boolean,
        val sleepQuality: Int,
        val time: SleepSessionTime,
        val soundsRecorded: Int,
        val mood: String
)