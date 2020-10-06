package com.github.tomplum.activity.dto.activity

data class InitialiseResponse(val snapshotDates: SnapshotDatesResponse) {
    companion object {
        fun empty() = InitialiseResponse(SnapshotDatesResponse(emptyList()))
    }
}