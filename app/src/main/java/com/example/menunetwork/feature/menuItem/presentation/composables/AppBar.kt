package com.example.menunetwork.feature.menuItem.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.menunetwork.R
import com.example.menunetwork.feature.menuItem.presentation.MenuViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppBar(viewModel: MenuViewModel) {
    val state by viewModel.state.collectAsState()
    val categories = listOf("Appetizer", "Salad", "Beverage", "Other")


    Column {
        TopAppBar(
            title = {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.little_lemon_logo),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .height(32.dp)
                            .padding(bottom = 8.dp)
                    )
                }
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { category ->
                FilterChip(
                    selected = state.selectedCategories.contains(category),
                    onClick = {
                        val updated = if (state.selectedCategories.contains(category)) {
                            state.selectedCategories - category
                        } else {
                            state.selectedCategories + category
                        }
                        viewModel.onEvent(MenuEvent.CategoryFilterChanged(updated))
                    },
                    label = { Text(category) }
                )
            }
        }
    }
}
