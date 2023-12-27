package com.br.lealapps.di

import com.br.lealapps.presentation.HomeActivity
import com.br.lealapps.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(homeActivity: HomeActivity)
    fun inject(mainActivity: MainActivity)
}
