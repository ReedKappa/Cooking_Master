package com.example.cookingmaster.di

import android.app.Application
import android.content.Context
import com.example.cookingmaster.data.repository.ReceiptRepository
import com.example.cookingmaster.data.repository.ReceiptRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface AppBindsModule {

    @Binds
    @Singleton
    fun bindReceiptRepository(repositoryImpl: ReceiptRepositoryImpl): ReceiptRepository

    companion object {
        @Provides
        fun provideContext(app: Application): Context =
            app.applicationContext
    }
}