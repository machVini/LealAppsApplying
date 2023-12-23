package com.br.lealapps.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.lealapps.data.source.model.Exercicio
import com.br.lealapps.data.source.model.Treino
import com.br.lealapps.domain.model.RepositoryResult
import com.br.lealapps.domain.usecase.AddExercicioUseCase
import com.br.lealapps.domain.usecase.AddTreinoUseCase
import com.br.lealapps.domain.usecase.DeleteExercicioUseCase
import com.br.lealapps.domain.usecase.DeleteTreinoUseCase
import com.br.lealapps.domain.usecase.GetExerciciosUseCase
import com.br.lealapps.domain.usecase.GetTreinosUseCase
import com.br.lealapps.domain.usecase.UpdateExercicioUseCase
import com.br.lealapps.domain.usecase.UpdateTreinoUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val addTreinoUseCase: AddTreinoUseCase,
    private val getTreinosUseCase: GetTreinosUseCase,
    private val updateTreinoUseCase: UpdateTreinoUseCase,
    private val deleteTreinoUseCase: DeleteTreinoUseCase,
    private val addExercicioUseCase: AddExercicioUseCase,
    private val getExerciciosUseCase: GetExerciciosUseCase,
    private val updateExercicioUseCase: UpdateExercicioUseCase,
    private val deleteExercicioUseCase: DeleteExercicioUseCase
) : ViewModel() {
    private val _treinos = MutableLiveData<List<Treino>>()
    val treinos: LiveData<List<Treino>> get() = _treinos
    private val _exercicios = MutableLiveData<List<Exercicio>>()
    val exercicios: LiveData<List<Exercicio>> get() = _exercicios


    fun addTreino(treino: Treino) {
        viewModelScope.launch {
            addTreinoUseCase(treino)
            loadTreinos()
        }
    }

    fun loadTreinos() {
        viewModelScope.launch {
            val result = getTreinosUseCase()
            if (result is RepositoryResult.Success)
                _treinos.value = result.data
        }
    }

    fun updateTreino(treino: Treino) {
        viewModelScope.launch {
            updateTreinoUseCase(treino)
            loadTreinos()
        }
    }

    fun deleteTreino(treinoId: Int) {
        viewModelScope.launch {
            deleteTreinoUseCase(treinoId)
            loadTreinos()
        }
    }

    fun addExercicio(exercicio: Exercicio) {
        viewModelScope.launch {
            addExercicioUseCase(exercicio)
            loadExercicios()
        }
    }

    fun loadExercicios() {
        viewModelScope.launch {
            val result = getExerciciosUseCase()
            if (result is RepositoryResult.Success)
                _exercicios.value = result.data
        }
    }

    fun updateExercicio(exercicio: Exercicio) {
        viewModelScope.launch {
            updateExercicioUseCase(exercicio)
            loadExercicios()
        }
    }

    fun deleteExercicio(exercicioId: Int) {
        viewModelScope.launch {
            deleteExercicioUseCase(exercicioId)
            loadExercicios()
        }
    }
}
