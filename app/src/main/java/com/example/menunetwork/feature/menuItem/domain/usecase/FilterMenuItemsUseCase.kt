package com.example.menunetwork.feature.menuItem.domain.usecase

import com.example.menunetwork.feature.menuItem.data.local.MenuItemEntity
import com.example.menunetwork.feature.menuItem.domain.MenuItemRepository
import kotlinx.coroutines.flow.Flow

class FilterMenuItemsUseCase(
    private val repository: MenuItemRepository
) {
    operator fun invoke(
        categories: Set<String>,
        query: String
    ): Flow<List<MenuItemEntity>> {
        return repository.getFiltered(categories, query)
    }
}
