package com.example.menunetwork.feature.menuItem.presentation.composables

import com.example.menunetwork.feature.menuItem.data.local.MenuItemEntity

data class MenuUIState(
    val items: List<MenuItemEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val selectedCategories: Set<String> = emptySet()
)
