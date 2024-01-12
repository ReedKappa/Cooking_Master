package com.example.cookingmaster.di

import com.example.cookingmaster.data.api.ReceiptService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideReceiptService() : ReceiptService =
        Retrofit.Builder()
            .client(
                OkHttpClient.Builder().addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY)).build())
            .baseUrl("http://45.8.158.244/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ReceiptService::class.java)
}