package com.example.tipcalculator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.serialization.Serializable
import androidx.navigation.toRoute

@Serializable object BillEntry
@Serializable data class Result(val billAmount: Float, val people: Int)
@Serializable object TipPicker
@Serializable object GuideScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = BillEntry) {

        composable<BillEntry> {
            BillEntryScreen(onCalculateClick = { billAmount, peopleCount ->
                navController.navigate(Result(billAmount, peopleCount))
            })
        }

        composable<Result> { backStackEntry ->
            val args = backStackEntry.toRoute<Result>()
            val billAmount = args.billAmount
            val people = args.people
            val tipPercent by backStackEntry.savedStateHandle
                .getStateFlow(key = "tipPercent", initialValue = 15)
                .collectAsState()
            TipResultScreen(
                billAmount = billAmount,
                people = people,
                tipPercent = tipPercent,
                onGuideScreenClick = { navController.navigate(GuideScreen) },
                onPickPercentClick = { navController.navigate(TipPicker) },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable<TipPicker> {
            TipPercentPickerScreen(onPercentChosen = { percent ->
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("tipPercent", percent)
                navController.popBackStack()
            })
        }

        composable<GuideScreen> {
            TippingGuidScreen(onBackClick = {
                navController.popBackStack()
            })
        }
    }
}