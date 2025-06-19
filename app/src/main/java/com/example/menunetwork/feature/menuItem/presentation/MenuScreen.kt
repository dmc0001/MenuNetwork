package com.example.menunetwork.feature.menuItem.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.menunetwork.feature.menuItem.presentation.composables.MenuEvent
import com.example.menunetwork.feature.menuItem.presentation.composables.MenuItemCard

@Composable
fun MenuScreen(viewModel: MenuViewModel,modifier: Modifier ) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(MenuEvent.LoadMenu)
    }


    if (state.isLoading) {
        Box(Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            CircularProgressIndicator()

        }
    } else if (state.error != null) {
        Box(contentAlignment = Alignment.Center){
            Text("Error: ${state.error}")
        }

    } else {

        Column (modifier.padding(horizontal = 16.dp )){
            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = { viewModel.onEvent(MenuEvent.SearchQueryChanged(it)) },
                modifier = Modifier.fillMaxWidth(),
                        label = { Text("Search menu...") },
            )

            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(state.items) { item ->
                    MenuItemCard(
                        item = item.toDomain()
                    )
                }
            }
        }
    }
}

