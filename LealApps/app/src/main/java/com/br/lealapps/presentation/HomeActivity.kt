package com.br.lealapps.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.br.lealapps.data.source.remote.FirestoreDatabaseService
import com.br.lealapps.domain.mapper.DocumentReferenceToExercicioMapperImpl
import com.br.lealapps.domain.usecase.AddExercicioUseCaseImpl
import com.br.lealapps.domain.usecase.AddTreinoUseCaseImpl
import com.br.lealapps.domain.usecase.DeleteExercicioUseCaseImpl
import com.br.lealapps.domain.usecase.DeleteTreinoUseCaseImpl
import com.br.lealapps.domain.usecase.GetExerciciosUseCaseImpl
import com.br.lealapps.domain.usecase.GetTreinosUseCaseImpl
import com.br.lealapps.domain.usecase.UpdateExercicioUseCaseImpl
import com.br.lealapps.domain.usecase.UpdateTreinoUseCaseImpl
import com.br.lealapps.presentation.screen.HomeNavigation
import com.br.lealapps.presentation.viewmodel.HomeViewModel
import com.br.lealapps.presentation.viewmodel.HomeViewModelFactory
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : ComponentActivity() {
    private val firestoreDatabaseService = FirestoreDatabaseService(FirebaseFirestore.getInstance())
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(
            addTreinoUseCase = AddTreinoUseCaseImpl(firestoreDatabaseService),
            getTreinosUseCase = GetTreinosUseCaseImpl(firestoreDatabaseService),
            updateTreinoUseCase = UpdateTreinoUseCaseImpl(firestoreDatabaseService),
            deleteTreinoUseCase = DeleteTreinoUseCaseImpl(firestoreDatabaseService),
            addExercicioUseCase = AddExercicioUseCaseImpl(firestoreDatabaseService),
            getExerciciosUseCase = GetExerciciosUseCaseImpl(firestoreDatabaseService),
            updateExercicioUseCase = UpdateExercicioUseCaseImpl(firestoreDatabaseService),
            deleteExercicioUseCase = DeleteExercicioUseCaseImpl(firestoreDatabaseService),
            documentReferenceToExercicioMapper = DocumentReferenceToExercicioMapperImpl(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HomeNavigation(viewModel)
        }
    }
}