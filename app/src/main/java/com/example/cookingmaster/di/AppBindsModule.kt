package com.example.cookingmaster.di

import android.app.Application
import android.content.Context
import com.example.cookingmaster.CookingMasterApplication
import com.example.cookingmaster.data.repository.ReceiptRepository
import com.example.cookingmaster.data.repository.ReceiptRepositoryImpl
import com.example.cookingmaster.domain.DeleteReceiptUseCase
import com.example.cookingmaster.domain.DeleteReceiptUseCaseImpl
import com.example.cookingmaster.domain.GetAllReceiptsUseCase
import com.example.cookingmaster.domain.GetAllReceiptsUseCaseImpl
import com.example.cookingmaster.domain.GetIngredientsByIdUseCase
import com.example.cookingmaster.domain.GetIngredientsByIdUseCaseImpl
import com.example.cookingmaster.domain.GetReceiptByIdUseCase
import com.example.cookingmaster.domain.GetReceiptByIdUseCaseImpl
import com.example.cookingmaster.domain.GetReceiptByNameUseCase
import com.example.cookingmaster.domain.GetReceiptByNameUseCaseImpl
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

    @Binds
    fun bindGetIngredientsByIdUseCase(useCase: GetIngredientsByIdUseCaseImpl): GetIngredientsByIdUseCase

    @Binds
    fun bindGetReceiptByIdUseCase(useCase: GetReceiptByIdUseCaseImpl): GetReceiptByIdUseCase

    @Binds
    fun bindGetReceiptByNameUseCase(useCase: GetReceiptByNameUseCaseImpl): GetReceiptByNameUseCase

    companion object {
        @Provides
        fun provideContext(app: Application): Context =
            app.applicationContext
    }
}