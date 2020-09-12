package activity.sleep

import java.time.LocalDateTime

data class SleepSession(
        val startDate: LocalDateTime,
        val endDate: LocalDateTime,
        val duration: Double,
        val isNap: Boolean,
        val sleepQuality: Int,
        val awakeTime: Double,
        val remSleep: Double,
        val lightSleep: Double,
        val deepSleep: Double,
        val soundsRecorded: Int,
        val mood: Mood
)