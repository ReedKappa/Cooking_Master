package com.example.cookingmaster.di

import android.content.Context
import com.example.cookingmaster.CookingMasterApplication

val Context.appComponent: AppComponent
    get() = when (this) {
        is CookingMasterApplication -> appComponent
        else -> applicationContext.appComponent
    }