package com.github.tomplum.activity

import com.github.tomplum.activity.sleep.Mood

fun String.toMood() = Mood.fromString(this)