import activity.sleep.Mood

fun String.toMood() = Mood.fromString(this)