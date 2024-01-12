package com.example.cookingmaster.data.api

import com.example.cookingmaster.data.model.ReceiptModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ReceiptService {

    @GET("dish/by_name/")
    suspend fun getReceiptByName(@Query("name") receiptName: String): Response<List<ReceiptModel>>
}