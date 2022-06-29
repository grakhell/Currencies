package ru.grakhell.currencies.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import ru.grakhell.currencies.R
import ru.grakhell.currencies.ui.viewmodel.CurrenciesViewModel
import ru.grakhell.currencies.ui.vo.CurrencyObject

@Composable
fun CurrenciesScreen(
    vm: CurrenciesViewModel = getViewModel()) {

    val itemsFlow = vm.state

    Scaffold(
        bottomBar = {
            BottomAppBar {
                Text(text = stringResource(id = R.string.currencies_screen_title))
            }
        }
    ) {

    }
}

@Composable
fun CurrencyList(currencies: List<CurrencyObject>, onClick:(CurrencyObject)-> Unit) {
    LazyColumn(Modifier.verticalScroll(rememberScrollState())
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