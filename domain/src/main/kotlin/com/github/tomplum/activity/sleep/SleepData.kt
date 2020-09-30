package com.github.tomplum.activity.sleep

data class SleepData(val snapshots: List<SleepSnapshot>) {
    fun getDates() = snapshots.map { it.date }

    fun getMostRecentSnapshot() = snapshots.maxByOrNull { it.date }
}