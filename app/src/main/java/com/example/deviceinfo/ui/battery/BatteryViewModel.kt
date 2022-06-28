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
import com.example.deviceinfo.model.Health
import com.example.deviceinfo.model.Source
import com.example.deviceinfo.model.Status

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
            BatteryManager.BATTERY_HEALTH_COLD -> Health.COLD
            BatteryManager.BATTERY_HEALTH_GOOD -> Health.GOOD
            BatteryManager.BATTERY_HEALTH_DEAD -> Health.DEAD
            BatteryManager.BATTERY_HEALTH_OVERHEAT -> Health.OVERHEAT
            BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> Health.OVER_VOLTAGE
            BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> Health.UNSPECIFIED_FAILURE
            else -> Health.UNKNOWN

        }
        val source = when (intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)) {
            BatteryManager.BATTERY_PLUGGED_AC -> Source.PLUGGED_AC
            BatteryManager.BATTERY_PLUGGED_USB -> Source.PLUGGED_USB
            BatteryManager.BATTERY_PLUGGED_WIRELESS -> Source.PLUGGED_WIRELESS
            else -> Source.NO_POWER
        }

        val status = when (intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)) {
            BatteryManager.BATTERY_STATUS_FULL -> Status.FULL
            BatteryManager.BATTERY_STATUS_CHARGING -> Status.CHARGING
            BatteryManager.BATTERY_STATUS_DISCHARGING -> Status.DIS_CHARGING
            BatteryManager.BATTERY_STATUS_NOT_CHARGING -> Status.NOT_CHARGING
            else -> Status.NO_STATUS
        }
        isCharging.value = status == Status.CHARGING
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