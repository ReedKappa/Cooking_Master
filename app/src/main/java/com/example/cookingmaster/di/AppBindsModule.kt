package com.example.cookingmaster.di

import android.app.Application
import android.content.Context
import com.example.cookingmaster.data.repository.ReceiptRepository
import com.example.cookingmaster.data.repository.ReceiptRepositoryImpl
import com.example.cookingmaster.domain.DeleteReceiptUseCase
import com.example.cookingmaster.domain.DeleteReceiptUseCaseImpl
import com.example.cookingmaster.domain.GetAllReceiptsUseCase
import com.example.cookingmaster.domain.GetAllReceiptsUseCaseImpl
import com.example.cookingmaster.domain.UpsertReceiptsUseCase
import com.example.cookingmaster.domain.UpsertReceiptsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface AppBindsModule {

    @Binds
    @Singleton
    fun bindReceiptRepository(repositoryImpl: ReceiptRepositoryImpl): ReceiptRepository

    @Binds
    fun bindDeleteReceiptUseCase(useCase: DeleteReceiptUseCaseImpl): DeleteReceiptUseCase

    @Binds
    fun bindUpsertReceiptUseCase(useCase: UpsertReceiptsUseCaseImpl): UpsertReceiptsUseCase

    @Binds
    fun bindGetAllReceiptsUseCase(useCase: GetAllReceiptsUseCaseImpl): GetAllReceiptsUseCase

    companion object {
        @Provides
        fun provideContext(app: Application): Context =
            app.applicationContext
    }
}