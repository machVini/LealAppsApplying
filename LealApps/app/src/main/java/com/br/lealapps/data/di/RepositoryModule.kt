package com.br.lealapps.data.di

import com.br.lealapps.data.repository.AuthRepository
import com.br.lealapps.data.repository.FirebaseAuthRepository
import com.br.lealapps.data.repository.FirestoreFitnessRepository
import com.br.lealapps.data.repository.FitnessRepository
import com.br.lealapps.data.source.remote.AuthDataSource
import com.br.lealapps.data.source.remote.FirebaseAuthService
import com.br.lealapps.data.source.remote.FirestoreFitnessService
import com.br.lealapps.data.source.remote.FitnessDataSource
import com.br.lealapps.domain.mapper.FitnessResponseMapper
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindAuthRepository(firebaseAuthRepository: FirebaseAuthRepository): AuthRepository

    @Binds
    fun providesFitnessRepository(firestoreFitnessRepository: FirestoreFitnessRepository): FitnessRepository

}