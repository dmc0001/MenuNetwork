package com.example.menunetwork

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MenuItemApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MenuItemApplication)
            modules(menuModule)
        }
    }
}