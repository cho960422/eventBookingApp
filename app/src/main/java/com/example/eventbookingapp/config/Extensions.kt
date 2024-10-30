package com.example.eventbookingapp.config

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.parseByNow(): String {
    val now = LocalDateTime.now()
    val minuteSeconds = 1 * 60L
    val hourSeconds = minuteSeconds * 60L
    val daySeconds = hourSeconds * 24L
    val weekSeconds = daySeconds * 7L

    val diff: Long = Duration.between(this, now).seconds
    val unit: Long

    if (diff < minuteSeconds) {
        return "방금 전"
    }

    if (diff in minuteSeconds until hourSeconds) {
        unit = diff / minuteSeconds
        return "${unit}분 전"
    }

    if (diff in hourSeconds until daySeconds) {
        unit = diff / hourSeconds
        return "${unit}시간 전"
    }

    if (diff in daySeconds until weekSeconds) {
        unit = diff / daySeconds
        return "${unit}일 전"
    }

    unit = diff / weekSeconds
    return "${unit}주 전"
}