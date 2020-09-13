package activity.sleep

import java.time.LocalDateTime

data class SleepSession(
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val duration: Int,
        val isNap: Boolean,
        val sleepQuality: Int,
        val awakeTime: Int,
        val remSleep: Int,
        val lightSleep: Int,
        val deepSleep: Int,
        val soundsRecorded: Int,
        val mood: Mood
)