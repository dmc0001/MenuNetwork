package com.example.menunetwork.feature.menuItem.data

import android.util.Log
import com.example.menunetwork.core.networking.utils.ApiResults
import com.example.menunetwork.feature.menuItem.data.local.MenuDao
import com.example.menunetwork.feature.menuItem.data.local.MenuItemEntity
import com.example.menunetwork.feature.menuItem.data.network.MenuItemService
import com.example.menunetwork.feature.menuItem.domain.MenuItemRepository
import kotlinx.coroutines.flow.Flow

class MenuItemRepositoryImp(
    private val menuItemService: MenuItemService,
    private val menuDao: MenuDao
) : MenuItemRepository {



    override suspend fun getMenuItems() {
        val result = menuItemService.fetchMenuItem()
        when (result) {

            is ApiResults.Success -> {
                val menuEntities = result.data.map { it.toEntity() }

                    menuDao.insertAll(menuEntities)

            }
            is ApiResults.Error -> {
                //TODO: handle this case
            }

        }
    }

    override fun getFiltered(
        categories: Set<String>,
        query: String
    ): Flow<List<MenuItemEntity>> {
        val categoryList = categories.toList()
        val matchAll = categories.isEmpty()
        return menuDao.getFiltered(categoryList, query, matchAll)
    }
}
