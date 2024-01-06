package com.example.cookingmaster

import android.app.Application
import com.example.cookingmaster.di.AppComponent
import com.example.cookingmaster.di.DaggerAppComponent

class CookingMasterApplication: Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
    }
}