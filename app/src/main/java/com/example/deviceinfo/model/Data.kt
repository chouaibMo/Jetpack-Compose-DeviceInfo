package com.example.deviceinfo.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Data(
    val label: String,
    val value: String,
    val icon: ImageVector
)

enum class BatteryStatus(val label : String) {
    FULL("Full"),
    CHARGING("Charging"),
    NOT_CHARGING("Not charging"),
    DIS_CHARGING("Discharging"),
    NO_STATUS("No status")
}

enum class BatteryHealth(val label: String) {
    COLD("Cold"),
    GOOD("Good"),
    DEAD("Dead"),
    OVERHEAT("Overheat"),
    OVER_VOLTAGE("Over-voltage"),
    UNSPECIFIED_FAILURE("Unspecified failure"),
    UNKNOWN("Unknown")
}

enum class BatterySource(val label: String) {
    PLUGGED_AC("AC Adapter"),
    PLUGGED_USB("USB charger"),
    PLUGGED_WIRELESS("Wireless charger"),
    NO_POWER("No power")
}

enum class BluetoothScanMode(val label: String) {
    NONE("None"),
    CONNECTABLE("Connectable"),
    CONNECTABLE_DISCOVERABLE("Connectable/Discoverable"),
    UNKNOWN("UNKNOWN")
}

enum class BluetoothState(val label: String) {
    OFF("Off"),
    ON("On"),
    TURNING_ON("Turning on"),
    TURNING_OFF("Turning off"),
    UNKNOWN("Unknown")
}

enum class WifiState(val label: String) {
    ENABLED("Enabled"),
    DISABLED("Disabled"),
}

enum class WifiFrequency(val label: String) {
    FREQUENCY_2_4("2.4Ghz"),
    FREQUENCY_5("5Ghz"),
    FREQUENCY_6("6Ghz"),
    UNKNOWN("UNKNOWN")
}