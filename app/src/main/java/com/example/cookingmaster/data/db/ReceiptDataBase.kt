package com.example.cookingmaster.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cookingmaster.data.db.model.ReceiptEntity

@Database(
    entities = [
        ReceiptEntity::class,
    ],
    version = 1
)
abstract class ReceiptDataBase: RoomDatabase() {
    abstract val receiptDAO: ReceiptDAO
}