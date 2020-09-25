package com.github.tomplum.activity.document

data class SleepSessionData(
        val startDate: String,
        val endDate: String,
        val duration: Int,
        val isNap: Boolean,
        val sleepQuality: Int,
        val awakeTime: Int,
        val remSleep: Int,
        val lightSleep: Int,
        val deepSleep: Int,
        val soundsRecorded: Int,
        val mood: String
)