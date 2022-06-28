package com.example.deviceinfo.ui.network

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.net.wifi.WifiManager
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bluetooth
import androidx.compose.material.icons.outlined.SettingsEthernet
import androidx.compose.material.icons.outlined.SettingsRemote
import androidx.compose.material.icons.outlined.Wifi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.deviceinfo.model.BluetoothScanMode
import com.example.deviceinfo.model.BluetoothState
import com.example.deviceinfo.model.Data
import com.example.deviceinfo.model.WifiState
import com.example.deviceinfo.ui.common.InfoCardsList


@Composable
fun NetworkScreen() {
    val bluetoothManager = LocalContext.current.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    val wifiManager = LocalContext.current.getSystemService(Context.WIFI_SERVICE) as WifiManager

    val wifiState = if(wifiManager.isWifiEnabled) WifiState.ENABLED else WifiState.DISABLED
    val bluetoothAddress = bluetoothManager.adapter.address
    val bluetoothState = when(bluetoothManager.adapter.state) {
        BluetoothAdapter.STATE_ON -> BluetoothState.ON
        BluetoothAdapter.STATE_OFF -> BluetoothState.OFF
        BluetoothAdapter.STATE_TURNING_ON -> BluetoothState.TURNING_ON
        BluetoothAdapter.STATE_TURNING_OFF -> BluetoothState.TURNING_OFF
        else -> BluetoothState.UNKNOWN
    }
    val bluetoothScanMode = when(bluetoothManager.adapter.scanMode) {
        BluetoothAdapter.SCAN_MODE_NONE -> BluetoothScanMode.NONE
        BluetoothAdapter.SCAN_MODE_CONNECTABLE -> BluetoothScanMode.CONNECTABLE
        BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE -> BluetoothScanMode.CONNECTABLE_DISCOVERABLE
        else -> BluetoothScanMode.UNKNOWN
    }


    val list = listOf(
        Data("Wifi", wifiState.label, Icons.Outlined.Wifi),
        Data("Bluetooth", bluetoothState.label, Icons.Outlined.Bluetooth),
        Data("Bluetooth address", bluetoothAddress, Icons.Outlined.SettingsEthernet),
        Data("Bluetooth scan mode", bluetoothScanMode.label, Icons.Outlined.SettingsRemote),
    )

    InfoCardsList(list = list)
}