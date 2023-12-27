package com.br.lealapps.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.br.lealapps.presentation.screen.HomeNavigation
import com.br.lealapps.presentation.viewmodel.HomeViewModel

class HomeActivity : ComponentActivity() {

    private val viewModel : HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HomeNavigation(viewModel)
        }
    }
}