package activity.sleep

import activity.sleep.Mood.*
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullAndEmptySource
import org.junit.jupiter.params.provider.ValueSource

class MoodTest {
    @Nested
    inner class FromString {
        @Test
        fun great() {
            assertThat(Mood.fromString("Great")).isEqualTo(GREAT)
        }

        @Test
        fun good() {
            assertThat(Mood.fromString("Good")).isEqualTo(GOOD)
        }

        @Test
        fun ok() {
            assertThat(Mood.fromString("Ok")).isEqualTo(OK)
        }

        @Test
        fun notGood() {
            assertThat(Mood.fromString("Not Good")).isEqualTo(NOT_SO_GOOD)
        }

        @Test
        fun bad() {
            assertThat(Mood.fromString("Bad")).isEqualTo(BAD)
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = ["N/A", "123", "BAD", "GREAT", "NoT GoOd"])
        fun unknownOrInvalid(value: String?) {
            assertThat(Mood.fromString(value)).isEqualTo(UNKNOWN)
        }
    }
}