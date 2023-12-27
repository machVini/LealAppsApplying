package com.br.lealapps.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.br.lealapps.domain.model.Treino
import com.br.lealapps.domain.utils.toTreinoDetailData
import com.br.lealapps.presentation.screen.common.CommonNavigationBar
import com.br.lealapps.presentation.screen.common.ComposableAlertExclusion
import com.br.lealapps.presentation.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
            TreinoItem(treino, navController, viewModel)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun TreinoItem(treino: Treino, navController: NavController, viewModel: HomeViewModel) {
    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onTreinoItemClick(treino, navController) },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = treino.nome,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    )
                    Box {
                        IconButton(
                            onClick = { expanded = true },
                            modifier = Modifier
                                .size(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More Options"
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        ) {
                            DropdownMenuItem(
                                onClick = {
                                    expanded = false
                                },
                                text = { Text(text = "Editar") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Editar Treino"
                                    )
                                }
                            )
                            DropdownMenuItem(
                                onClick = {
                                    expanded = false
                                    showDialog = true
                                },
                                text = { Text(text = "Apagar") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Apagar Treino"
                                    )
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Descrição: ${treino.descricao}", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = treino.data.toTreinoDetailData(),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { onTreinoItemClick(treino, navController) },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Ver Exercícios")
                }
            }
        }
    }

    if (showDialog)
        ComposableAlertExclusion(
            setShowDialog = { showDialog = it },
            title = "Apagar treino",
            subtitle = "Tem certeza que deseja deletar o treino?",
            onConfirmButton = { viewModel.deleteTreino(treino.nome) }
        )
}

private fun onTreinoItemClick(
    treino: Treino,
    navController: NavController
) {
    navController.navigate("trainingDetailScreen/${treino.nome}")
}
