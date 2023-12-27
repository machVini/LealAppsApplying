package com.br.lealapps.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.br.lealapps.data.repository.FirebaseAuthRepository
import com.br.lealapps.data.source.remote.FirebaseAuthService
import com.br.lealapps.domain.usecase.SignInUseCaseImpl
import com.br.lealapps.domain.usecase.SignUpUseCaseImpl
import com.br.lealapps.presentation.screen.AppNavigation
import com.br.lealapps.presentation.theme.LealAppsTheme
import com.br.lealapps.presentation.viewmodel.AuthViewModel
import com.br.lealapps.presentation.viewmodel.AuthViewModelFactory
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {

    private val repository = FirebaseAuthRepository(
        dataSource = FirebaseAuthService(FirebaseAuth.getInstance())
    )
    private val viewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(
            SignUpUseCaseImpl(repository),
            SignInUseCaseImpl(repository),
        )
    }

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