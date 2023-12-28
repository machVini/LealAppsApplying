package com.br.lealapps.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.br.lealapps.domain.model.Treino
import com.br.lealapps.presentation.screen.common.CommonNavigationBar
import com.br.lealapps.presentation.viewmodel.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTrainingScreen(navController: NavController, viewModel: HomeViewModel, treino: Treino) {
    val exercicios by viewModel.exercicios.observeAsState(emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Treino") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary,
                ),
            )
        },
        content = {
            CreateOrUpdateTraining(
                exercicios = exercicios,
                navController,
                treino,
                onSaveClick = { viewModel.updateTreino(treino.nome, it) }
            )
        },
        bottomBar = { CommonNavigationBar(navController = navController) },
    )
}
