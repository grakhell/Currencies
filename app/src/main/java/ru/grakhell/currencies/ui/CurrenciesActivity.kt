package ru.grakhell.currencies.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.grakhell.currencies.ui.screen.CurrenciesScreen
import ru.grakhell.currencies.ui.screen.ExRatesScreen
import ru.grakhell.currencies.ui.theme.CurrenciesTheme
import ru.grakhell.currencies.ui.vo.CurrencyObject
import java.sql.Date
import java.text.SimpleDateFormat

class CurrenciesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrenciesTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    Column {
        val controller = rememberNavController()
        Scaffold() {
            AppNavGraph(navController = controller)
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screens.CurrenciesScreen.route, modifier = modifier) {
        composable(Screens.CurrenciesScreen.route) {
            CurrenciesScreen(navController)
        }
        composable(route = Screens.RatesScreen.route, arguments = listOf(
            navArgument("currency") { type = NavType.StringType},
            navArgument("date") {
                nullable = true
                type = NavType.StringType}
        )) {
            Log.d("nav", "AppNavGraph: routing")
            val curr = it.arguments?.getString("currency")
            requireNotNull(curr)
            val d = it.arguments?.getString("date")?:""
            ExRatesScreen(controller = navController, currency =  CurrencyObject(curr, ""), date = d )
        }
    }
}