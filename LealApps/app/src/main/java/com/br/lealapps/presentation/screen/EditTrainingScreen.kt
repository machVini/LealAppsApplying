package com.br.lealapps.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.br.lealapps.domain.model.Treino
import com.br.lealapps.presentation.screen.common.CommonNavigationBar
import com.br.lealapps.presentation.screen.common.CommonTopBar
import com.br.lealapps.presentation.viewmodel.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditTrainingScreen(navController: NavController, viewModel: HomeViewModel, treino: Treino) {
    val exercicios by viewModel.exercicios.observeAsState(emptyList())
    Scaffold(
        topBar = {
            CommonTopBar("Editar Treino", navController)
        },
        content = {
            CreateOrUpdateTraining(
                exercicios = exercicios,
                treino,
                onSaveClick = { treinoNovo ->
                    navController.popBackStack()
                    viewModel.updateTreino(
                        treinoAntigoName = treino.nome,
                        treinoNovo = treinoNovo
                    )
                }
            )
        },
        bottomBar = { CommonNavigationBar(navController = navController) },
    )
}
