package com.example.tipcalculator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "billEntry") {
        composable("billEntry") {
            BillEntryScreen(onCalculateClick = { billAmount, peopleCount ->
                navController.navigate("result/$billAmount/$peopleCount")
            })
        }
        composable(
            route = "result/{billAmount}/{people}",
            arguments = listOf(
                navArgument("billAmount") { type = NavType.FloatType },
                navArgument("people") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val billAmount = backStackEntry.arguments
                ?.getFloat("billAmount") ?: 0f
            val people = backStackEntry.arguments
                ?.getInt("people") ?: 1
            val tipPercent by backStackEntry.savedStateHandle
                .getStateFlow("tipPercent", 15)
                .collectAsState()
            TipResultScreen(
                billAmount = billAmount,
                people = people,
                tipPercent = tipPercent,
                onGuideScreenClick = { navController.navigate("guideScreen") },
                onPickPercentClick = { navController.navigate("tipPicker") },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable("tipPicker") {
            TipPercentPickerScreen(onPercentChosen = { percent ->
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("tipPercent", percent)
                navController.popBackStack()
            })
        }
        composable("guideScreen") {
            TippingGuidScreen(onBackClick = {
                navController.popBackStack()
            })
        }
    }
}