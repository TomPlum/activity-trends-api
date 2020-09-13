package activity.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class SleepData(
        val startDate: String,
        val endDate: String,
        val duration: Double,
        val isNap: Boolean,
        val sleepQuality: Int,
        val awakeTime: Double,
        val remSleep: Double,
        val lightSleep: Double,
        val deepSleep: Double,
        val soundsRecorded: Int,
        val mood: String
)