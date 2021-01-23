package com.github.tomplum.activity.exceptions

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class DataNotFoundTest {
    @Test
    fun message() {
        assertThat(DataNotFound("Sleep Data Not Found").message).isEqualTo("Sleep Data Not Found")
    }
}
