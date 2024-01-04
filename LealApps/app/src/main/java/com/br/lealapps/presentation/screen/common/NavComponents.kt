package com.br.lealapps.presentation.screen.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CommonNavigationBar(navController: NavController) {
    var selectedItem by remember { mutableIntStateOf(2) }
    val items = listOf("Treinos", "Exercicios")

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier.height(68.dp)
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = rememberUpdatedState(selectedItem == index)

            NavigationBarItem(
                icon = {
                    Icon(
                        getIconForItem(item),
                        contentDescription = item,
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                label = { Text(item, color = MaterialTheme.colorScheme.primary) },
                selected = isSelected.value,
                onClick = {
                    selectedItem = index
                    getNavigation(index, navController)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopBar(
    title: String,
    navController: NavController,
    actions: @Composable (RowScope.() -> Unit) = {},
    navigationIconAction: () -> Unit = { navController.popBackStack()},
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = navigationIconAction) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Voltar")
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            titleContentColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
            actionIconContentColor = MaterialTheme.colorScheme.primary,
        ),
        actions = actions
    )
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