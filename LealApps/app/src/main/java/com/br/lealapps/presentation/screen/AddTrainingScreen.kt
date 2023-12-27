package com.br.lealapps.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.br.lealapps.domain.model.Exercicio
import com.br.lealapps.domain.model.Treino
import com.br.lealapps.domain.utils.toBrazilianDate
import com.br.lealapps.domain.utils.toBrazilianDateFormat
import com.br.lealapps.presentation.screen.common.CommonNavigationBar
import com.br.lealapps.presentation.viewmodel.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTrainingScreen(navController: NavController, viewModel: HomeViewModel) {
    val exercicios by viewModel.exercicios.observeAsState(emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adicionar Treino") },
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
            AddingTraining(exercicios = exercicios, viewModel = viewModel, navController)
        },
        bottomBar = { CommonNavigationBar(navController = navController) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingTraining(
    exercicios: List<Exercicio>,
    viewModel: HomeViewModel,
    navController: NavController
) {
    var nome by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var data by remember { mutableStateOf("") }
    var exerciciosSelecionados by remember { mutableStateOf(emptyList<Exercicio>()) }
    val context = LocalContext.current
    var showDatePickerDialog by remember {
        mutableStateOf(false)
    }
    val datePickerState = rememberDatePickerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 72.dp)
    ) {
        // Campo de Nome do Treino
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome do Treino") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Campo de Descrição do Treino
        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text("Descrição do Treino") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        if (showDatePickerDialog) {
            DatePickerDialog(
                onDismissRequest = { showDatePickerDialog = false },
                confirmButton = {
                    Button(
                        onClick = {
                            datePickerState
                                .selectedDateMillis?.let { millis ->
                                    data = millis.toBrazilianDateFormat()
                                }
                            showDatePickerDialog = false
                        }) {
                        Text(text = "Escolher data")
                    }
                }) {
                DatePicker(state = datePickerState)
            }
        }

        OutlinedTextField(
            value = data,
            onValueChange = { },
            label = { Text("Data do Treino") },
            trailingIcon = {
                IconButton(onClick = { showDatePickerDialog = !showDatePickerDialog }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Data do Treino"
                    )
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Lista de Exercícios com Checkboxes
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 16.dp)
        ) {
            itemsIndexed(exercicios) { _, exercicio ->
                val isChecked = exerciciosSelecionados.contains(exercicio)
                var checkboxState by remember { mutableStateOf(isChecked) }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Checkbox(
                        checked = checkboxState,
                        onCheckedChange = {
                            hideKeyboard(context = context)
                            checkboxState = it
                            exerciciosSelecionados = if (it) {
                                exerciciosSelecionados + exercicio
                            } else {
                                exerciciosSelecionados - exercicio
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clickable { checkboxState = !checkboxState }
                    )
                    Text(
                        text = exercicio.nome,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
        Button(
            onClick = {
                viewModel.addTreino(
                    Treino(
                        nome = nome,
                        descricao = descricao,
                        data = data.toBrazilianDate(),
                        exercicios = exerciciosSelecionados
                    )
                )
                hideKeyboard(context = context)
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(imageVector = Icons.Default.Save, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Salvar")
        }
    }
}
