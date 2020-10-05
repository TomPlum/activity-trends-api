package com.github.tomplum.activity.dto.sleep

data class SleepSnapshotResponse(val date: String, val sessions: List<SleepSessionResponse>)