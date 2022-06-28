package com.example.deviceinfo.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Data(
    val label: String,
    val value: String,
    val icon: ImageVector
)

enum class Status(val label : String) {
    FULL("Full"),
    CHARGING("Charging"),
    NOT_CHARGING("Not charging"),
    DIS_CHARGING("Discharging"),
    NO_STATUS("No status")
}

enum class Health(val label: String) {
    COLD("Cold"),
    GOOD("Good"),
    DEAD("Dead"),
    OVERHEAT("Overheat"),
    OVER_VOLTAGE("Over-voltage"),
    UNSPECIFIED_FAILURE("Unspecified failure"),
    UNKNOWN("Unknown")
}

enum class Source(val label: String) {
    PLUGGED_AC("AC Adapter"),
    PLUGGED_USB("USB charger"),
    PLUGGED_WIRELESS("Wireless charger"),
    NO_POWER("No power")
}