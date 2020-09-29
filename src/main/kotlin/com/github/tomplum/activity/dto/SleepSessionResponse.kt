package com.github.tomplum.activity.dto

data class SleepSessionResponse(
        val startDate: String,
        val endDate: String,
        val duration: Int,
        val isNap: Boolean,
        val sleepQuality: Int,
        val time: SleepTimeResponse,
        val soundsRecorded: Int,
        val mood: String
)