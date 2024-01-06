package com.example.cookingmaster.di

import android.app.Application
import com.example.cookingmaster.presenter.home_fragment.HomeFragment
import com.example.cookingmaster.presenter.receipt_fragment.ReceiptFragment
import com.example.cookingmaster.presenter.search_fragment.SearchFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class
    ]
)
@Singleton
interface AppComponent {

    fun inject(fragment: HomeFragment)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: ReceiptFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }
}


@Module(
    includes = [
        AppBindsModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        DatabaseModule::class
    ]
)
class AppModule