package com.br.lealapps.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.br.lealapps.data.source.model.Exercicio
import com.br.lealapps.presentation.viewmodel.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExercisesScreen(navController: NavController, viewModel: HomeViewModel) {
    val exercicios by viewModel.exercicios.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Exercícios") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("addExerciseScreen") }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    }
                }
            )
        },
        content = {
            ExerciciosList(exercicios = exercicios, viewModel = viewModel)
        },
        bottomBar = { CommonNavigationBar(navController = navController) }
    )
}

@Composable
fun ExerciciosList(exercicios: List<Exercicio>, viewModel: HomeViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 72.dp)
    ) {
        itemsIndexed(exercicios) { _, exercicio ->
            ExercicioItem(exercicio, onExercicioClick = { exercicioAtualizado ->
                viewModel.loadExercicios()
                viewModel.loadTreinos()
            })
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ExercicioItem(exercicio: Exercicio, onExercicioClick: (Exercicio) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onExercicioClick(exercicio) }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
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
