package com.example.menunetwork.feature.menuItem.domain.usecase

data class MenuItemUseCases(
    val getMenuItems: GetMenuItemsUseCase,
    val filterMenuItems: FilterMenuItemsUseCase
)