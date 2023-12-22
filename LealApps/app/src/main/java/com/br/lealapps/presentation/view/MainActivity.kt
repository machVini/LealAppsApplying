package com.br.lealapps.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.lealapps.data.source.remote.FirebaseAuthService
import com.br.lealapps.domain.viewmodel.AuthViewModel
import com.br.lealapps.presentation.theme.LealAppsTheme

class MainActivity : ComponentActivity() {

    private val viewModel: AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LealAppsTheme {
                MainActivityScreen(AuthViewModel(FirebaseAuthService()))
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainActivityScreen(viewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val user = viewModel.authenticatedUser.observeAsState()

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisibility = !passwordVisibility }
                ) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Button(
                onClick = {
                    viewModel.signIn(email, password)
                    hideKeyboard(context)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text("Sign In")
            }

            Button(
                onClick = {
                    viewModel.signOut()
                    hideKeyboard(context)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text("Sign Out")
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Button(
                onClick = {
                    viewModel.createUserWithEmailAndPassword(email, password)
                    hideKeyboard(context)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text("Create User")
            }
        }

        Text(
            text = if (user.value?.email != null) "User logged \n" +
                    "Email: ${user.value?.email}" else "No user authenticated",
            fontSize = 18.sp
        )

        val loginStatus by viewModel.loginStatus.observeAsState()
        loginStatus?.let { status ->
            when (status) {
                AuthViewModel.LoginStatus.INVALID_CREDENTIALS -> {
                    SnackbarHost(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        hostState = SnackbarHostState(),
                        snackbar = {
                            Snackbar(
                                content = { Text("Credenciais inválidas. Verifique seu email e senha.") },
                                action = {
                                    // Ação opcional, por exemplo, fechar a Snackbar
                                }
                            )
                        }
                    )
                }

                AuthViewModel.LoginStatus.ERROR -> {
                    SnackbarHost(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        hostState = SnackbarHostState(),
                        snackbar = {
                            Snackbar(
                                content = { Text("Ocorreu um erro no login. Tente novamente mais tarde.") },
                                action = {
                                    // Ação opcional, por exemplo, fechar a Snackbar
                                }
                            )
                        }
                    )
                }

                else -> Unit
            }
        }
    }
}

fun hideKeyboard(context: android.content.Context) {
    val inputMethodManager =
        context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? android.view.inputmethod.InputMethodManager
    inputMethodManager?.hideSoftInputFromWindow(
        (context as? android.app.Activity)?.currentFocus?.windowToken,
        0
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMainActivityScreen() {
    LealAppsTheme {
        MainActivityScreen(AuthViewModel(FirebaseAuthService()))
    }
}