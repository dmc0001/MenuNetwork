package com.example.menunetwork.feature.menuItem.domain

import com.example.menunetwork.feature.menuItem.data.local.MenuDao
import com.example.menunetwork.feature.menuItem.data.local.MenuItemEntity
import com.example.menunetwork.feature.menuItem.data.network.MenuItemService
import kotlinx.coroutines.flow.Flow

interface MenuItemRepository {


    suspend fun getMenuItems()
    fun getFiltered(categories: Set<String>, query: String): Flow<List<MenuItemEntity>>
}