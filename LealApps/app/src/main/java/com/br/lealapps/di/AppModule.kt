package com.br.lealapps.di

import com.br.lealapps.domain.usecase.AddExercicioUseCase
import com.br.lealapps.domain.usecase.AddTreinoUseCase
import com.br.lealapps.domain.usecase.DeleteExercicioUseCase
import com.br.lealapps.domain.usecase.DeleteTreinoUseCase
import com.br.lealapps.domain.usecase.GetExerciciosUseCase
import com.br.lealapps.domain.usecase.GetTreinosUseCase
import com.br.lealapps.domain.usecase.SignInUseCase
import com.br.lealapps.domain.usecase.SignUpUseCase
import com.br.lealapps.domain.usecase.UpdateExercicioUseCase
import com.br.lealapps.domain.usecase.UpdateTreinoUseCase
import com.br.lealapps.presentation.viewmodel.AuthViewModel
import com.br.lealapps.presentation.viewmodel.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideHomeViewModel(
        addTreinoUseCase: AddTreinoUseCase,
        getTreinosUseCase: GetTreinosUseCase,
        updateTreinoUseCase: UpdateTreinoUseCase,
        deleteTreinoUseCase: DeleteTreinoUseCase,
        addExercicioUseCase: AddExercicioUseCase,
        getExerciciosUseCase: GetExerciciosUseCase,
        updateExercicioUseCase: UpdateExercicioUseCase,
        deleteExercicioUseCase: DeleteExercicioUseCase,
    ): HomeViewModel {
        return HomeViewModel(
            addTreinoUseCase = addTreinoUseCase,
            getTreinosUseCase = getTreinosUseCase,
            updateTreinoUseCase = updateTreinoUseCase,
            deleteTreinoUseCase = deleteTreinoUseCase,
            addExercicioUseCase = addExercicioUseCase,
            getExerciciosUseCase = getExerciciosUseCase,
            updateExercicioUseCase = updateExercicioUseCase,
            deleteExercicioUseCase = deleteExercicioUseCase,
        )
    }

    @Provides
    fun provideAuthViewModel(
        signUpUseCase: SignUpUseCase,
        signInUseCase: SignInUseCase
    ): AuthViewModel {
        return AuthViewModel(signUpUseCase, signInUseCase)
    }

}



