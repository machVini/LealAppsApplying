package com.br.lealapps.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.br.lealapps.data.source.model.Exercicio
import com.br.lealapps.presentation.screen.common.CommonNavigationBar
import com.br.lealapps.presentation.screen.common.ComposableAlertExclusion
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
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary,
                ),
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
            ExercicioItem(exercicio, viewModel)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ExercicioItem(exercicio: Exercicio, viewModel: HomeViewModel) {
    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                viewModel.loadExercicios()
                viewModel.loadTreinos()
            },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                exercicio.imagem.let { imageUrl ->
                    val painter = rememberImagePainter(imageUrl)
                    Image(
                        painter = painter,
                        contentDescription = "Imagem do exercício",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = exercicio.nome,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Obs: ${exercicio.observacoes}", fontSize = 14.sp)
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
                                        contentDescription = "Editar Exercicio"
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
                                        contentDescription = "Apagar Exercicio"
                                    )
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        if (showDialog)
            ComposableAlertExclusion(
                setShowDialog = { showDialog = it },
                title = "Apagar Exercício",
                subtitle = "Tem certeza que deseja deletar o exercício?",
                onConfirmButton = { viewModel.deleteExercicio(exercicio.nome) }
            )
    }

}
