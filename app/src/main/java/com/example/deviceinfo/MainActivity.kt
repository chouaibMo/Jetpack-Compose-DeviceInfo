package com.example.deviceinfo

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.deviceinfo.ui.battery.BatteryViewModel
import com.example.deviceinfo.ui.theme.DeviceInfoTheme

class MainActivity : ComponentActivity() {

    private val batteryViewModel : BatteryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        registerReceiver(batteryViewModel.batteryChangedReceiver, IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_CHANGED)
        })
        setContent {
            DeviceInfoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(viewModel = batteryViewModel)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryViewModel.batteryChangedReceiver)
    }
}

@Composable
fun MainScreen(viewModel: BatteryViewModel) {
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopBar() },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Navigation(navController, viewModel)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DeviceInfoTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            MainScreen(BatteryViewModel())
        }
    }
}