package com.example.deviceinfo

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BatteryChargingFull
import androidx.compose.material.icons.rounded.Devices
import androidx.compose.material.icons.rounded.NetworkWifi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.deviceinfo.ui.battery.BatteryScreen
import com.example.deviceinfo.ui.battery.BatteryViewModel
import com.example.deviceinfo.ui.device.DeviceScreen


internal sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Battery : Screen("battery", "Battery", Icons.Rounded.BatteryChargingFull)
    object Device : Screen("device", "Device", Icons.Rounded.Devices)
    object Network : Screen("network", "Network", Icons.Rounded.NetworkWifi)
}

@Composable
fun Navigation(navController: NavHostController, viewModel: BatteryViewModel) {
    NavHost(navController, startDestination = Screen.Battery.route) {
        composable(Screen.Battery.route) {
            BatteryScreen(viewModel = viewModel)
        }
        composable(Screen.Device.route) {
            DeviceScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(Screen.Battery, Screen.Device, Screen.Network)

    BottomNavigation(
        elevation = 8.dp,
        contentColor = Color.White,
        modifier = Modifier
            .padding(8.dp, 0.dp, 8.dp, 16.dp)
            .graphicsLayer {
                shape = RoundedCornerShape(14.dp)
                clip = true
            },
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = true,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 20.sp) },
    )
}
