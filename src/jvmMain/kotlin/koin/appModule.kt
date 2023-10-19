package koin

import api.KtorClient
import org.koin.dsl.module
import splash.api.SplashApiModule
import splash.api.SplashSM

val appModule = module {
    factory { KtorClient }
    factory { SplashApiModule(get()) }
    factory { SplashSM(get()) }
//    viewModel { SplashViewModel(get()) }
}