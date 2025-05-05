package com.devname.chickenchronicles.utility

import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970

fun currentTimeSeconds(): Long {
    // Current timestamp in seconds
    return NSDate().timeIntervalSince1970().toLong()
}