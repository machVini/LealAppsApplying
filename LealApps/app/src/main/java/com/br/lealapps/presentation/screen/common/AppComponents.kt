package com.br.lealapps.presentation.screen.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ComposableAlertExclusion(
    onConfirmButton: () -> Unit = {},
    onDismissButton: () -> Unit = {},
    setShowDialog: (Boolean) -> Unit = {},
    title: String,
    subtitle: String,
) {
    AlertDialog(
        onDismissRequest = { setShowDialog(false) },
        confirmButton = {
            TextButton(onClick = {
                onConfirmButton()
                setShowDialog(false)
            }) {
                Text("Apagar")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismissButton()
                setShowDialog(false)
            }) {
                Text("Cancelar")
            }
        },
        icon = { Icon(Icons.Filled.Delete, null) },
        title = { Text(text = title) },
        text = { Text(text = subtitle) },
    )
}