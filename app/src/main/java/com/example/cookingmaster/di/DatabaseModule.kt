package com.example.cookingmaster.di

import android.content.Context
import androidx.room.Room
import com.example.cookingmaster.data.db.ReceiptDAO
import com.example.cookingmaster.data.db.ReceiptDataBase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideDataBase(context: Context): ReceiptDataBase =
        Room.databaseBuilder(
            context,
            ReceiptDataBase::class.java,
            "receipts.db"
        ).build()

    @Provides
    fun provideReceiptDAO(db: ReceiptDataBase): ReceiptDAO =
        db.receiptDAO
}