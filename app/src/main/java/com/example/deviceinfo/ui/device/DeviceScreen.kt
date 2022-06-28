package com.example.deviceinfo.ui.device

import android.os.Build
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.Language
import androidx.compose.runtime.Composable
import com.example.deviceinfo.model.Data
import com.example.deviceinfo.ui.common.InfoCardsList
import java.util.*

@Composable
fun DeviceScreen() {
    val list = listOf(
        Data("Brand", Build.BRAND, Icons.Outlined.Storefront),
        Data("Device", Build.DEVICE, Icons.Outlined.PhonelinkSetup),
        Data("Model", Build.MODEL, Icons.Outlined.LocalMall),
        Data("Product", Build.PRODUCT, Icons.Outlined.Inventory),
        Data("Release", Build.VERSION.RELEASE, Icons.Outlined.NewReleases),
        Data("Sdk", Build.VERSION.SDK_INT.toString(), Icons.Outlined.Android),
        Data("Manufacturer", Build.MANUFACTURER, Icons.Outlined.PrecisionManufacturing),
        Data("Hardware", Build.HARDWARE, Icons.Outlined.Memory),
        Data("Language", Locale.getDefault().language, Icons.Rounded.Language),
    )

    InfoCardsList(list = list)
}