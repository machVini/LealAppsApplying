package com.br.lealapps.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.br.lealapps.domain.model.Exercicio
import com.br.lealapps.presentation.screen.common.CommonNavigationBar
import com.br.lealapps.presentation.screen.common.CommonTopBar
import com.br.lealapps.presentation.viewmodel.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditExerciseScreen(
    navController: NavController,
    viewModel: HomeViewModel,
    exercicio: Exercicio
) {
    Scaffold(
        topBar = {
            CommonTopBar("Adicionar Exercicio", navController)
        },
        content = {
            CreateOrUpdateExercise(
                exercicio = exercicio,
                onSaveClick = { exercicioNovo ->
                    navController.popBackStack()
                    viewModel.updateExercicio(
                        exercicioAntigoName = exercicio.nome,
                        exercicioNovo = exercicioNovo
                    )
                }
            )
        },
        bottomBar = { CommonNavigationBar(navController = navController) },
    )
}