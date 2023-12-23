package com.br.lealapps.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.br.lealapps.data.source.remote.FirebaseAuthService
import com.br.lealapps.domain.viewmodel.AuthViewModel
import com.br.lealapps.domain.viewmodel.AuthViewModelFactory
import com.br.lealapps.presentation.screen.SignInScreen
import com.br.lealapps.presentation.screen.SignUpScreen
import com.br.lealapps.presentation.theme.LealAppsTheme


class MainActivity : ComponentActivity() {
    private val firebaseAuthService = FirebaseAuthService()
    private val viewModel: AuthViewModel by viewModels { AuthViewModelFactory(firebaseAuthService) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LealAppsTheme {
                Navigation(viewModel)
            }
        }
    }
}

@Composable
fun Navigation(viewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "loginScreen") {
        composable("loginScreen") { SignInScreen(navController, viewModel) }
        composable("signUpScreen") { SignUpScreen(navController, viewModel) }
    }
}