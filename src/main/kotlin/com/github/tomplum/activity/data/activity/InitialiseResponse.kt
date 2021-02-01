package com.github.tomplum.activity.data.activity

data class InitialiseResponse(val snapshotDates: SnapshotDatesResponse) {
    companion object {
        fun empty() = InitialiseResponse(SnapshotDatesResponse(emptyList()))
    }
}