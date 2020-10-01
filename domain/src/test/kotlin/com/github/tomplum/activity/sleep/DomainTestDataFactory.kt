package com.github.tomplum.activity.sleep

import java.time.LocalDate
import java.time.LocalDateTime

//TODO: Move to test module
class DomainTestDataFactory {
    companion object {
        /**
         * Snapshot Recorded @ 1st October 2020
         * @see sleepSession
         */
        fun snapshot() = SleepSnapshot(LocalDate.of(2020, 10, 1), listOf(sleepSession()))

        /**
         * - Snapshots Recorded:
         * 1st October 2020
         * 14th October 2020
         * 3rd November 2020
         * @see sleepSession
         */
        fun snapshots() = listOf(
                SleepSnapshot(LocalDate.of(2020, 10, 1), listOf(sleepSession())),
                SleepSnapshot(LocalDate.of(2020, 10, 14), listOf(sleepSession())),
                SleepSnapshot(LocalDate.of(2020, 11, 3), listOf(sleepSession()))
        )

        /**
         * - Session Information:
         * 30th September 2020 23:30 -> 31st September 2020 07:30 (8 Hours Slept)
         * Breakdown: 30m Awake / 1h30m Light / 4h Deep / 30m REM
         * 12 Sounds Recorded
         * Great Mood
         */
        private fun sleepSession() = SleepSession(
                LocalDateTime.of(2020, 9, 29, 23, 30),
                LocalDateTime.of(2020, 9, 30, 7, 30),
                480,
                false,
                83,
                SleepTime(30,180, 240, 30),
                12,
                Mood.GREAT
        )
    }
}