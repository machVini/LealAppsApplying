package com.br.lealapps.data.di

import com.br.lealapps.data.repository.AuthRepository
import com.br.lealapps.data.repository.FirebaseAuthRepository
import com.br.lealapps.data.repository.FirestoreFitnessRepository
import com.br.lealapps.data.repository.FitnessRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindAuthRepository(firebaseAuthRepository: FirebaseAuthRepository): AuthRepository

    @Binds
    fun providesFitnessRepository(firestoreFitnessRepository: FirestoreFitnessRepository): FitnessRepository

}