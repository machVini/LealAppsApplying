package com.br.lealapps.domain.model

sealed class AuthError(val errorMessage: String) {
    object InvalidCredentials : AuthError("Username/password incorrect")
    object OtherError : AuthError("Could not authenticate you. Try again later")
    object CreateUserError : AuthError("Error to create an user")
    object UnmatchPasswordsError : AuthError("The passwords don't match")
}