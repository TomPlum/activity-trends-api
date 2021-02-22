package com.github.tomplum.activity.workout.route

import java.time.LocalDateTime

data class ExerciseRoute(
    val data: Route,
    val creationDate: LocalDateTime,
    val source: HealthDataSource
)