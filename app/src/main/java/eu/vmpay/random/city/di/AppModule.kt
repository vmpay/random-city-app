package eu.vmpay.random.city.di

import eu.vmpay.random.city.repository.Producer
import eu.vmpay.random.city.repository.Repository
import eu.vmpay.random.city.repository.local.AppDatabase
import eu.vmpay.random.city.viewmodel.ViewModelFactory
import kotlinx.coroutines.channels.Channel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { Repository(get(), get<AppDatabase>().cityDao()) }
    single { ViewModelFactory(get()) }
    single { Channel<Pair<String, String>>() }
    single { Producer(get()) }
    single { AppDatabase.getInstance(androidContext()) }
}
