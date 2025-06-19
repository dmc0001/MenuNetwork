package com.example.menunetwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold

import androidx.compose.ui.Modifier
import com.example.menunetwork.feature.menuItem.presentation.MenuScreen
import com.example.menunetwork.feature.menuItem.presentation.composables.AppBar
import com.example.menunetwork.feature.menuItem.presentation.MenuViewModel
import com.example.menunetwork.ui.theme.MenuNetworkTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MenuNetworkTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                        topBar = {
                            AppBar(viewModel = koinViewModel<MenuViewModel>())
                        }
                ) { innerPadding ->
                    MenuScreen(
                        viewModel = koinViewModel<MenuViewModel>(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

}



