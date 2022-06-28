package com.example.deviceinfo.ui.battery

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.deviceinfo.ui.common.InfoCardsList


@Composable
fun BatteryScreen(viewModel: BatteryViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .padding(top = 35.dp)
                .fillMaxWidth()
        ) {
            BatteryCirleProgress(
                isCharging = viewModel.isCharging.value,
                percentage = viewModel.batteryLevel.value,
                fillColor = MaterialTheme.colors.primary,
                backgroundColor = Color.LightGray,
                strokeWidth = 15.dp
            )
        }
        InfoCardsList(list = viewModel.dataList)
    }
}

@Preview
@Composable
fun PreviewBatteryScreen() {
    BatteryScreen(viewModel = BatteryViewModel())
}