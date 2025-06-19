package com.example.menunetwork.feature.menuItem.domain.usecase


import com.example.menunetwork.feature.menuItem.domain.MenuItemRepository

class GetMenuItemsUseCase(
    private val repository: MenuItemRepository
) {
    suspend operator fun invoke() {
        repository.getMenuItems()
    }
}
