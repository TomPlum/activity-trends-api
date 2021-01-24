package com.github.tomplum.activity.xml.sleep

data class SleepSessionResponse(
        val startDate: String,
        val endDate: String,
        val duration: Int,
        val isNap: Boolean,
        val quality: Int,
        val time: SleepTimeResponse,
        val soundsRecorded: Int,
        val mood: String
)