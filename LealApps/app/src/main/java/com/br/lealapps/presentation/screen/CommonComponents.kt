package com.br.lealapps.presentation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

@Composable
fun CommonNavigationBar(navController: NavController) {
    var selectedItem by remember { mutableStateOf(2) }
    val items = listOf("Treinos", "Exercicios")

    NavigationBar {
        items.forEachIndexed { index, item ->
            val isSelected = rememberUpdatedState(selectedItem == index)

            NavigationBarItem(
                icon = { Icon(getIconForItem(item), contentDescription = item) },
                label = { Text(item) },
                selected = isSelected.value,
                onClick = {
                    selectedItem = index
                    getNavigation(index, navController)
                }
            )
        }
    }
}

private fun getIconForItem(item: String): ImageVector =
    when (item) {
        "Treinos" -> Icons.Default.Home
        "Exercicios" -> Icons.Default.FitnessCenter
        else -> Icons.Default.Favorite
    }

private fun getNavigation(index: Int, navController: NavController) {
    when (index) {
        0 -> navController.navigate("homeScreen")
        1 -> navController.navigate("exercisesScreen")
        else -> navController.navigate("homeScreen")
    }
}