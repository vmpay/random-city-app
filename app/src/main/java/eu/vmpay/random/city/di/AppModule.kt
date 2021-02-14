package eu.vmpay.random.city.di

import eu.vmpay.random.city.repository.Repository
import eu.vmpay.random.city.viewmodel.ViewModelFactory
import org.koin.dsl.module

val appModule = module {
    single { Repository() }
    single { ViewModelFactory(get()) }
}