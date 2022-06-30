package ru.grakhell.currencies.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import ru.grakhell.currencies.R
import ru.grakhell.currencies.ui.Screens
import ru.grakhell.currencies.ui.viewmodel.ExRatesViewModel
import ru.grakhell.currencies.ui.vo.CurrencyObject
import ru.grakhell.currencies.ui.vo.ExRateObject
import ru.grakhell.currencies.ui.vo.UiFlow
import java.util.*

@Composable
fun ExRatesScreen(
    controller: NavHostController,
    currency: CurrencyObject,
    date: String,
    vm: ExRatesViewModel = getViewModel(parameters = { parametersOf(currency,date) })
) {
    val itemsFlow = vm.state.collectAsState()
    DrawRatesContent(controller = controller, state = itemsFlow, currency, date)
}

@Composable
fun DrawRatesContent(
    controller: NavHostController,
    state: State<UiFlow>,
    currency: CurrencyObject,
    date: String,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val d = if (date.isNotBlank()) date else stringResource(id = R.string.rates_today)
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomAppBar {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                    IconButton(onClick = { controller.popBackStack(Screens.CurrenciesScreen.route, inclusive = false) }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = stringResource(id = R.string.action_back))
                    }
                }

                Text(text = stringResource(id = R.string.rates_screen_title, currency.index, d), modifier = Modifier.padding(start =  8.dp))
            }
        }) {
        val flow = state.value
        when (flow) {
            is UiFlow.Error -> {
                LaunchedEffect(scaffoldState.snackbarHostState) {
                    scaffoldState.snackbarHostState.showSnackbar(flow.cause.toString())
                }
            }
            is UiFlow.GotExRates -> {
                RatesList(rates = flow.rates.rates)
            }
            is UiFlow.InProgress -> {
                LaunchedEffect(scaffoldState.snackbarHostState) {
                    scaffoldState.snackbarHostState.showSnackbar("Загрузка")
                }
            }
            else -> {
                LaunchedEffect(scaffoldState.snackbarHostState) {
                    scaffoldState.snackbarHostState.showSnackbar("Ошибка состояния")
                }
            }
        }
    }
}

@Composable
fun RatesList(rates: List<ExRateObject>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        state = rememberLazyListState()
    )  {
        items(rates) { rate ->
            RatesItem(item = rate)
        }
    }
}

@Composable
fun RatesItem(item : ExRateObject) {
    Row( Modifier.padding(top = 4.dp, bottom = 4.dp)) {
        Column(Modifier.padding(start = 8.dp, end = 8.dp)) {
            Text(text = item.currencyIndex, style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ))
        }
        Column(Modifier.padding(start = 8.dp, end = 8.dp)) {
            Text(text = item.rate.toString(), style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ))
        }
    }
}