package util

fun logcat(message: String) {
    logcat("@@@:: $message")
}

fun logError(message: String) {
    logcat("@@@logError:: $message")
}

fun logSuccess(message: String) {
    logcat("@@@logSuccess:: $message")
}