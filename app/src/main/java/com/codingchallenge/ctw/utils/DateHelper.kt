package com.codingchallenge.ctw.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.toDate(): String? {
    val parser = SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.getDefault())
    val formatter = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
    return parser.parse(this.toString())?.let { formatter.format(it) }
}

fun Date.toTime(): String? {
    val parser = SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.getDefault())
    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    return parser.parse(this.toString())?.let { formatter.format(it) }
}