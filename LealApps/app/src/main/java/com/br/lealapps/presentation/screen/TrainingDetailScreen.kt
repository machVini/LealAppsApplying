package com.br.lealapps.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.br.lealapps.data.source.model.Exercicio
import com.br.lealapps.data.source.model.Treino
import com.br.lealapps.presentation.viewmodel.HomeViewModel
import kotlinx.datetime.DayOfWeek

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingDetailScreen(
    navController: NavController,
    viewModel: HomeViewModel,
    treino: Treino,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes do Treino") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("addExerciseScreen") }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Options")
                    }
//                    IconButton(onClick = { navController.navigate("editTrainingScreen") }) {
//                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
//                    }
//                    IconButton(onClick = { viewModel.deleteTreino(treino.nome) }) {
//                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
//                    }
                },
            )
        },
        content = {
            TreinoDetailInfoItem(treino = treino, viewModel)
        },
        bottomBar = { CommonNavigationBar(navController = navController) },
    )
}

@Composable
fun TreinoDetailInfoItem(treino: Treino, viewModel: HomeViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 72.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = treino.nome,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Muscles: ${treino.descricao}", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Day: ${DayOfWeek(treino.data?.day!!)} - ${treino.data?.toLocaleString()}",
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            LaunchedEffect(viewModel) {
                val exerciciosState: List<Exercicio> =
                    viewModel.mapListDocumentReferencesToExercicios(treino.exercicios)

                viewModel.setExerciciosState(exerciciosState)
            }

            val exerciciosState: List<Exercicio> by viewModel.exerciciosState.collectAsState(initial = emptyList())
            TrainingDetailExerciciosList(exerciciosState, viewModel)
        }
    }
}

@Composable
fun TrainingDetailExerciciosList(exercicios: List<Exercicio>, viewModel: HomeViewModel) {
    LazyColumn() {
        itemsIndexed(exercicios) { _, exercicio ->
            TrainingDetailExercicioItem(exercicio, onExercicioClick = { exercicioAtualizado ->
                viewModel.loadExercicios()
                viewModel.loadTreinos()
            })
        }
    }
}

@Composable
fun TrainingDetailExercicioItem(exercicio: Exercicio, onExercicioClick: (Exercicio) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onExercicioClick(exercicio) }
    ) {
        Column {
            Text(
                text = "Nome: ${exercicio.nome}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            Spacer(modifier = Modifier.height(8.dp))
            //Image(painter = , contentDescription = )
            //Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Obs: ${exercicio.observacoes}", fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}



