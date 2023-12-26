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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.br.lealapps.data.source.model.Treino
import com.br.lealapps.presentation.screen.common.CommonNavigationBar
import com.br.lealapps.presentation.viewmodel.HomeViewModel
import kotlinx.datetime.DayOfWeek

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Treinos") },
                actions = {
                    IconButton(onClick = { navController.navigate("addTrainingScreen") }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
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
            TreinosList(viewModel = viewModel, navController = navController)
        },
        bottomBar = { CommonNavigationBar(navController = navController) }
    )
}

@Composable
fun TreinosList(viewModel: HomeViewModel, navController: NavController) {
    viewModel.loadTreinos()
    val treinos by viewModel.treinos.collectAsState(emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 72.dp)
    ) {
        itemsIndexed(treinos) { _, treino ->
            TreinoItem(treino, navController, onTreinoClick = { _ ->
                //viewModel.updateTreino(treinoUpdated)
            })
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun TreinoItem(treino: Treino, navController: NavController, onTreinoClick: (Treino) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onTreinoItemClick(onTreinoClick, treino, navController) },
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
            Button(
                onClick = { onTreinoItemClick(onTreinoClick, treino, navController) },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Ver Exercícios")
            }
        }
    }
}

private fun onTreinoItemClick(
    onTreinoClick: (Treino) -> Unit,
    treino: Treino,
    navController: NavController
) {
    onTreinoClick(treino)
    navController.navigate("trainingDetailScreen/${treino.nome}")
}
