package com.github.tomplum.activity.sleep

import java.time.LocalDateTime

data class SleepSession(
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val duration: Int,
        val isNap: Boolean,
        val sleepQuality: Int,
        val time: SleepTime,
        val soundsRecorded: Int,
        val mood: Mood
)