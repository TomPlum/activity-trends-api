package activity.document

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class SleepData(
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