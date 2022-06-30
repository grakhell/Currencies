package ru.grakhell.currencies.ui.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.getViewModel
import ru.grakhell.currencies.R
import ru.grakhell.currencies.ui.Screens
import ru.grakhell.currencies.ui.viewmodel.CurrenciesViewModel
import ru.grakhell.currencies.ui.vo.CurrencyObject
import ru.grakhell.currencies.ui.vo.UiFlow

@Composable
fun CurrenciesScreen(
    controller: NavHostController,
    vm: CurrenciesViewModel = getViewModel()) {
    val itemsFlow = vm.state.collectAsState()
    DrawCurrenciesContent(controller = controller, state = itemsFlow, vm)
}

@Composable
fun DrawCurrenciesContent(
    controller: NavHostController,
    state: State<UiFlow>,
    vm: CurrenciesViewModel,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
        BottomAppBar(
            elevation = 4.dp
        ) {
            Text(text = stringResource(id = R.string.currencies_screen_title), modifier = Modifier.padding(start =  8.dp))
        }
    }) {
        val flow = state.value
        flow.effects.forEach { side ->
            when(side) {
                is  UiFlow.SideEffect.OnCurrencyClicked -> {
                    val f = side.currency
                    vm.onClickDispatched()
                    controller.navigate(Screens.RatesScreen.create(f.index, null))
                }
            }
        }
        when (flow) {
            is UiFlow.Error -> {
                LaunchedEffect(scaffoldState.snackbarHostState) {
                    scaffoldState.snackbarHostState.showSnackbar(flow.cause.toString())
                }
            }
            is UiFlow.GotCurrencies -> {
                CurrencyList(currencies = flow.list) { item -> vm.onItemClicked(item) }
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
fun CurrencyList(currencies: List<CurrencyObject>, onClick:(CurrencyObject)-> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        state = rememberLazyListState()
    )  {
        items(currencies) { currency ->
            CurrencyCard(item = currency, onClick = onClick )
        }
    }
}

@Composable
fun CurrencyCard(item : CurrencyObject, onClick:(CurrencyObject)-> Unit) {
    Column(
        Modifier
            .padding(top = 4.dp, bottom = 4.dp)
            .fillMaxWidth()
            .clickable { onClick.invoke(item) })
    {
        CurrencyItem(item = item)
    }
}

@Composable
fun CurrencyItem(item : CurrencyObject) {
    Column {
        Row(Modifier.padding(start = 8.dp, end = 8.dp)) {
            Text(text = item.index, style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ))
        }
        Row(Modifier.padding(start = 8.dp, end = 8.dp)) {
            Text(text = item.name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyListPreview() {
    CurrencyList(currencies = listOf(CurrencyObject("RUB", "Russian rouble"),
        CurrencyObject("EUR", "Euro"),
        CurrencyObject("USD", "Dollar"))) {}
}

@Preview(showBackground = true)
@Composable
fun CurrencyPreview() {
    CurrencyCard(item = CurrencyObject("RUB", "Russian rouble")) {}
}