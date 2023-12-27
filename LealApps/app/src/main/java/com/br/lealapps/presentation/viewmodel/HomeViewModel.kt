package com.br.lealapps.presentation.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.lealapps.data.source.model.result.RepositoryResult
import com.br.lealapps.domain.model.Exercicio
import com.br.lealapps.domain.model.Treino
import com.br.lealapps.domain.usecase.AddExercicioUseCase
import com.br.lealapps.domain.usecase.AddTreinoUseCase
import com.br.lealapps.domain.usecase.DeleteExercicioUseCase
import com.br.lealapps.domain.usecase.DeleteTreinoUseCase
import com.br.lealapps.domain.usecase.GetExerciciosUseCase
import com.br.lealapps.domain.usecase.GetTreinosUseCase
import com.br.lealapps.domain.usecase.UpdateExercicioUseCase
import com.br.lealapps.domain.usecase.UpdateTreinoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class HomeViewModel (
    private val addTreinoUseCase: AddTreinoUseCase,
    private val getTreinosUseCase: GetTreinosUseCase,
    private val updateTreinoUseCase: UpdateTreinoUseCase,
    private val deleteTreinoUseCase: DeleteTreinoUseCase,
    private val addExercicioUseCase: AddExercicioUseCase,
    private val getExerciciosUseCase: GetExerciciosUseCase,
    private val updateExercicioUseCase: UpdateExercicioUseCase,
    private val deleteExercicioUseCase: DeleteExercicioUseCase,
) : ViewModel() {
    private val _treinos = MutableStateFlow<List<Treino>>(emptyList())
    val treinos: StateFlow<List<Treino>> = _treinos

    private val _exercicios = MutableLiveData<List<Exercicio>>()
    val exercicios: LiveData<List<Exercicio>> get() = _exercicios

    private val _exerciciosState = MutableStateFlow<List<Exercicio>>(emptyList())
    val exerciciosState: StateFlow<List<Exercicio>> = _exerciciosState.asStateFlow()

    init {
        viewModelScope.launch {
            loadTreinos()
            loadExercicios()
        }
    }

    fun setExerciciosState(exercicios: List<Exercicio>) {
        _exerciciosState.value = exercicios
    }

    fun addTreino(treino: Treino) {
        viewModelScope.launch {
            when (val result = addTreinoUseCase(treino)) {
                is RepositoryResult.Success -> loadTreinos()
                is RepositoryResult.Error -> Log.e(TAG, "Error adding treino", result.exception)
            }
        }
    }

    fun loadTreinos() {
        viewModelScope.launch {
            when (val result = getTreinosUseCase()) {
                is RepositoryResult.Success -> _treinos.value = result.data.sortedBy { it.data }
                is RepositoryResult.Error -> Log.e(TAG, "Error loading treinos", result.exception)
            }
        }
    }

    fun updateTreino(treino: Treino) {
        viewModelScope.launch {
            updateTreinoUseCase(treino)
            loadTreinos()
        }
    }

    fun deleteTreino(treinoName: String) {
        viewModelScope.launch {
            deleteTreinoUseCase(treinoName)
            loadTreinos()
        }
    }

    fun addExercicio(exercicio: Exercicio) {
        viewModelScope.launch {
            when (val result = addExercicioUseCase(exercicio)) {
                is RepositoryResult.Success -> loadExercicios()
                is RepositoryResult.Error -> Log.e(TAG, "Error adding exercicio", result.exception)
            }
        }
    }

    fun loadExercicios() {
        viewModelScope.launch {
            when (val result = getExerciciosUseCase()) {
                is RepositoryResult.Success -> _exercicios.value = result.data.sortedBy { it.nome.uppercase() }
                is RepositoryResult.Error -> Log.e(
                    TAG,
                    "Error loading exercicios",
                    result.exception
                )
            }
        }
    }

    fun updateExercicio(exercicio: Exercicio) {
        viewModelScope.launch {
            when (val result = updateExercicioUseCase(exercicio)) {
                is RepositoryResult.Success -> loadExercicios() // Recarregar a lista após atualizar
                is RepositoryResult.Error -> Log.e(
                    TAG,
                    "Error updating exercicio",
                    result.exception
                )
            }
        }
    }

    fun deleteExercicio(exercicioName: String) {
        viewModelScope.launch {
            deleteExercicioUseCase(exercicioName)
            loadExercicios()
        }
    }

    fun getTreinoByName(treinoName: String): Treino? {
        return _treinos.value?.find { it.nome == treinoName }
    }
}
