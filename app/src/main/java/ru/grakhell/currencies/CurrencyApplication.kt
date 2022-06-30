package ru.grakhell.currencies

import android.app.Application
import androidx.compose.foundation.layout.Box
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.grakhell.currencies.data.DataModule
import ru.grakhell.currencies.domain.DomainModule
import ru.grakhell.currencies.ui.UIModule

class CurrencyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CurrencyApplication)
            modules(DataModule, DomainModule, UIModule)
        }
    }
}

