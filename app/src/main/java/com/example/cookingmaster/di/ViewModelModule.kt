package com.example.cookingmaster.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cookingmaster.presenter.home_fragment.HomeViewModel
import com.example.cookingmaster.presenter.receipt_fragment.ReceiptViewModel
import com.example.cookingmaster.presenter.search_fragment.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(model: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReceiptViewModel::class)
    fun bindReceiptViewModel(model: ReceiptViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(model: SearchViewModel): ViewModel
}