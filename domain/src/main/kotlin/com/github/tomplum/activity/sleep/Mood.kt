package com.github.tomplum.activity.sleep

enum class Mood(val value: String) {
    GREAT("Great"),
    GOOD("Good"),
    OK("Ok"),
    NOT_SO_GOOD("Not Good"),
    BAD("Bad"),
    UNKNOWN("N/A");

    companion object {
        fun fromString(value: String?) = when(value) {
            "Great" -> GREAT
            "Good" -> GOOD
            "Ok" -> OK
            "Not Good" -> NOT_SO_GOOD
            "Bad" -> BAD
            else -> UNKNOWN
        }
    }
}