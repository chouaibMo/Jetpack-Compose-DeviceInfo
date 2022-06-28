package com.example.deviceinfo.ui.battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.deviceinfo.model.Data
import com.example.deviceinfo.model.BatteryHealth
import com.example.deviceinfo.model.BatterySource
import com.example.deviceinfo.model.BatteryStatus

class BatteryViewModel : ViewModel() {

    val dataList = mutableStateListOf<Data>()
    val batteryLevel = mutableStateOf(-1)
    val isCharging = mutableStateOf(false)

    val batteryChangedReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            onActionBatteryChanged(intent)
        }
    }

    fun onActionBatteryChanged(intent: Intent) {
        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0).toDouble() / 1000
        val technology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY)
        val temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1) / 10

        val health = when (intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1)) {
            BatteryManager.BATTERY_HEALTH_COLD -> BatteryHealth.COLD
            BatteryManager.BATTERY_HEALTH_GOOD -> BatteryHealth.GOOD
            BatteryManager.BATTERY_HEALTH_DEAD -> BatteryHealth.DEAD
            BatteryManager.BATTERY_HEALTH_OVERHEAT -> BatteryHealth.OVERHEAT
            BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> BatteryHealth.OVER_VOLTAGE
            BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> BatteryHealth.UNSPECIFIED_FAILURE
            else -> BatteryHealth.UNKNOWN

        }
        val source = when (intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)) {
            BatteryManager.BATTERY_PLUGGED_AC -> BatterySource.PLUGGED_AC
            BatteryManager.BATTERY_PLUGGED_USB -> BatterySource.PLUGGED_USB
            BatteryManager.BATTERY_PLUGGED_WIRELESS -> BatterySource.PLUGGED_WIRELESS
            else -> BatterySource.NO_POWER
        }

        val status = when (intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)) {
            BatteryManager.BATTERY_STATUS_FULL -> BatteryStatus.FULL
            BatteryManager.BATTERY_STATUS_CHARGING -> BatteryStatus.CHARGING
            BatteryManager.BATTERY_STATUS_DISCHARGING -> BatteryStatus.DIS_CHARGING
            BatteryManager.BATTERY_STATUS_NOT_CHARGING -> BatteryStatus.NOT_CHARGING
            else -> BatteryStatus.NO_STATUS
        }
        isCharging.value = status == BatteryStatus.CHARGING
        batteryLevel.value = level
        dataList.clear()
        dataList.addAll(
            listOf(
                Data("Health", health.label, Icons.Outlined.HealthAndSafety),
                Data("Temperature", "${temperature}°C", Icons.Outlined.Thermostat),
                Data("Source", source.label, Icons.Outlined.Cable),
                Data("Status", status.label, Icons.Outlined.Power),
                Data("Technologie", technology.toString(), Icons.Outlined.Memory),
                Data("Voltage", "${voltage}V", Icons.Outlined.Bolt),
            )
        )
    }
}