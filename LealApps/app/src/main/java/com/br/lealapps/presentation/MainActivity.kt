package com.br.lealapps.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.br.lealapps.presentation.screen.AppNavigation
import com.br.lealapps.presentation.theme.LealAppsTheme
import com.br.lealapps.presentation.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LealAppsTheme {
                AppNavigation(viewModel)
            }
        }

        viewModel.authenticationState.observe(this, Observer { state ->
            when (state) {
                AuthViewModel.AuthenticationState.AUTHENTICATED -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                else -> {}
            }
        })
    }
}