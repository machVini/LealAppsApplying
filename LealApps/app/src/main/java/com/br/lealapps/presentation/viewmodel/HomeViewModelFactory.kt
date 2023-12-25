package com.br.lealapps.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.br.lealapps.domain.mapper.DocumentReferenceToExercicioMapper
import com.br.lealapps.domain.usecase.AddExercicioUseCase
import com.br.lealapps.domain.usecase.AddTreinoUseCase
import com.br.lealapps.domain.usecase.DeleteExercicioUseCase
import com.br.lealapps.domain.usecase.DeleteTreinoUseCase
import com.br.lealapps.domain.usecase.GetExerciciosUseCase
import com.br.lealapps.domain.usecase.GetTreinosUseCase
import com.br.lealapps.domain.usecase.UpdateExercicioUseCase
import com.br.lealapps.domain.usecase.UpdateTreinoUseCase

class HomeViewModelFactory(
    private val addTreinoUseCase: AddTreinoUseCase,
    private val getTreinosUseCase: GetTreinosUseCase,
    private val updateTreinoUseCase: UpdateTreinoUseCase,
    private val deleteTreinoUseCase: DeleteTreinoUseCase,
    private val addExercicioUseCase: AddExercicioUseCase,
    private val getExerciciosUseCase: GetExerciciosUseCase,
    private val updateExercicioUseCase: UpdateExercicioUseCase,
    private val deleteExercicioUseCase: DeleteExercicioUseCase,
    private val documentReferenceToExercicioMapper: DocumentReferenceToExercicioMapper,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(
                addTreinoUseCase,
                getTreinosUseCase,
                updateTreinoUseCase,
                deleteTreinoUseCase,
                addExercicioUseCase,
                getExerciciosUseCase,
                updateExercicioUseCase,
                deleteExercicioUseCase,
                documentReferenceToExercicioMapper,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}