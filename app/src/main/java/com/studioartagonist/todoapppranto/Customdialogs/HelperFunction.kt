package com.example.actioninputes

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*

@RequiresApi(Build.VERSION_CODES.N)
fun getFormattedDateTime(value: Long, format : String) : String {
    return SimpleDateFormat(format).format(Date(value))
}