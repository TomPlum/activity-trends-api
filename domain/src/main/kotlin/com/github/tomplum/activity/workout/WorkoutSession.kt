package com.github.tomplum.activity.workout

import java.time.LocalDateTime
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

data class WorkoutSession @ExperimentalTime constructor(
    val type: WorkoutType,
    val duration: Duration,
    val distance: Distance,
    val energyBurned: Energy,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
) {
}