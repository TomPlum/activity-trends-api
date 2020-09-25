package com.github.tomplum.activity.dto

data class SleepSnapshotResponse(val date: String, val sessions: List<SleepSessionResponse>)