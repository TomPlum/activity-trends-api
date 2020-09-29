package com.github.tomplum.activity.exceptions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class SnapshotNotFoundTest {
    @Test
    fun message() {
        assertThat(SnapshotNotFound("2020-09-29").message).isEqualTo("No Sleep Snapshot Found On 2020-09-29")
    }
}