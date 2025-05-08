package com.emirhanok20.chickenchronicles.utility

import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970
import kotlin.math.roundToInt

fun currentTimeSeconds(): Long {
    // Current timestamp in seconds
    return NSDate().timeIntervalSince1970().toLong()
}

fun Int.secondsToTimeStr(): String {
    val minutes = (this / 60f).roundToInt()
    val hours = minutes / 60
    return "$hours h ${minutes % 60} m"
}