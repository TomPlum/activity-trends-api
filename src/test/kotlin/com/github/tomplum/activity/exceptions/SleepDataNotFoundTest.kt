package com.github.tomplum.activity.exceptions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class SleepDataNotFoundTest {
    @Test
    fun message() {
        assertThat(SleepDataNotFound().message).isEqualTo("Sleep Data Not Found")
    }
}
