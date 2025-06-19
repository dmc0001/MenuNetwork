package com.example.menunetwork

import com.example.menunetwork.feature.menuItem.domain.usecase.FilterMenuItemsUseCase
import com.example.menunetwork.feature.menuItem.domain.usecase.GetMenuItemsUseCase
import com.example.menunetwork.feature.menuItem.domain.usecase.MenuItemUseCases
import com.example.menunetwork.feature.menuItem.presentation.MenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.example.menunetwork.feature.menuItem.data.MenuItemRepositoryImp
import com.example.menunetwork.feature.menuItem.data.network.MenuItemService
import com.example.menunetwork.feature.menuItem.domain.MenuItemRepository
import io.ktor.client.HttpClient
import androidx.room.Room
import android.app.Application
import com.example.menunetwork.core.networking.Client
import com.example.menunetwork.feature.menuItem.data.local.AppDatabase
import com.example.menunetwork.feature.menuItem.data.local.MenuDao
import io.ktor.client.engine.cio.CIO

val menuModule = module {

    // HttpClient
    single<HttpClient> {
        Client.create(engine = CIO.create())
    }

    // MenuItemService
    single { MenuItemService(get()) }

    // Room Database
    single {
        Room.databaseBuilder(
            get<Application>(),
            AppDatabase::class.java,
            "menu_db"
        ).build()
    }

    // MenuDao
    single<MenuDao> {
        get<AppDatabase>().menuDao()
    }

    // Repository
    single<MenuItemRepository> {
        MenuItemRepositoryImp(
            menuItemService = get(),
            menuDao = get()
        )
    }

    // UseCases
    factory { GetMenuItemsUseCase(get()) }
    factory { FilterMenuItemsUseCase(get()) }
    factory { MenuItemUseCases(get(), get()) }

    // ViewModel
    viewModel { MenuViewModel(get()) }
}

