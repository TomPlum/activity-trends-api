package com.github.tomplum.activity.sleep

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDate

class SleepDataTest {

    private val snapshot = DomainTestDataFactory.snapshot()
    private val snapshots = DomainTestDataFactory.snapshots()

    @Nested
    inner class GetSnapshotDates {
        @Test
        fun singleSnapshot() {
            val data = SleepData(listOf(snapshot))
            val dates = data.getDates()
            assertThat(dates).isEqualTo(listOf(LocalDate.of(2020, 10, 1)))
        }

        @Test
        fun multipleSnapshots() {
            val data = SleepData(snapshots)
            val dates = data.getDates()
            assertThat(dates).isEqualTo(listOf(LocalDate.of(2020,10,1), LocalDate.of(2020,10,14), LocalDate.of(2020,11,3)))
        }

        @Test
        fun noSnapshots() {
            val data = SleepData(emptyList())
            val dates = data.getDates()
            assertThat(dates).isEmpty()
        }
    }

    @Nested
    inner class GetMostRecentSnapshot {
        @Test
        fun singleSnapshot() {
            val data = SleepData(listOf(snapshot))
            val mostRecent = data.getMostRecentSnapshot()
            assertThat(mostRecent).isEqualTo(snapshot)
        }

        @Test
        fun multipleSnapshots() {
            val data = SleepData(snapshots)
            val mostRecent = data.getMostRecentSnapshot()
            assertThat(mostRecent).isEqualTo(snapshots.find { it.date == LocalDate.of(2020,11,3) })
        }

        @Test
        fun noSnapshots() {
            val data = SleepData(emptyList())
            val mostRecent = data.getMostRecentSnapshot()
            assertThat(mostRecent).isNull()
        }
    }
}