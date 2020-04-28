package com.testtask

import android.app.Application
import com.testtask.di.AppComponent
import com.testtask.di.AppModule
import com.testtask.di.DaggerAppComponent

class App : Application() {

    private var component: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getComponent(): AppComponent? {
        return component
    }

}
