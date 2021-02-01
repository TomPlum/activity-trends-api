package com.github.tomplum.activity.data.workouts

data class WorkoutSessionData (
    val type: String,
    val duration: String,
    val distance: String,
    val energyBurned: String,
    val startTime: String,
    val endTime: String,
    val meta: WorkoutMetaData,
    val routeName: String?
)