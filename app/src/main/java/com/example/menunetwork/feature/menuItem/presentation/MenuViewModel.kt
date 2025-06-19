package com.example.menunetwork.feature.menuItem.presentation

import androidx.lifecycle.ViewModel
import com.example.menunetwork.feature.menuItem.domain.usecase.MenuItemUseCases
import androidx.lifecycle.viewModelScope
import com.example.menunetwork.feature.menuItem.presentation.composables.MenuEvent
import com.example.menunetwork.feature.menuItem.presentation.composables.MenuUIState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MenuViewModel(
    private val useCases: MenuItemUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(MenuUIState())
    val state: StateFlow<MenuUIState> = _state.asStateFlow()

    fun onEvent(event: MenuEvent) {
        when (event) {
            is MenuEvent.LoadMenu -> loadMenuItems()
            is MenuEvent.Search -> searchItems(event.query, event.categories)
            is MenuEvent.SearchQueryChanged -> {
                _state.update { it.copy(searchQuery = event.query) }
                searchItems(event.query, emptySet())
            }
            is MenuEvent.CategoryFilterChanged -> {
                _state.update { it.copy(selectedCategories = event.selected) }
                searchItems(_state.value.searchQuery, event.selected)
            }
        }
    }

    private fun loadMenuItems() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            try {
                useCases.getMenuItems()
                // Optionally auto-load the cached list
                searchItems("", emptySet())
            } catch (e: Exception) {
                _state.update {
                    it.copy(isLoading = false, error = e.localizedMessage ?: "Unknown error")
                }
            }
        }
    }

    private fun searchItems(query: String, categories: Set<String>) {
        useCases.filterMenuItems(categories, query)
            .onEach { items ->
                _state.update {
                    it.copy(items = items, isLoading = false, error = null)
                }
            }
            .launchIn(viewModelScope)
    }
}
