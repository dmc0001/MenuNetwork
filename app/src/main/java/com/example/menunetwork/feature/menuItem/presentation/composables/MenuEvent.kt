package com.example.menunetwork.feature.menuItem.presentation.composables

sealed class MenuEvent {
    object LoadMenu : MenuEvent()
    data class Search(val query: String, val categories: Set<String>) : MenuEvent()
    data class SearchQueryChanged(val query: String) : MenuEvent()
    data class CategoryFilterChanged(val selected: Set<String>) : MenuEvent()
}
