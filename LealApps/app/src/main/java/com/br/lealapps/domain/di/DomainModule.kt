package com.br.lealapps.domain.di
//
//import com.br.lealapps.domain.mapper.FitnessResponseMapper
//import com.br.lealapps.domain.mapper.FitnessResponseMapperImpl
//import com.br.lealapps.domain.usecase.AddExercicioUseCase
//import com.br.lealapps.domain.usecase.AddExercicioUseCaseImpl
//import com.br.lealapps.domain.usecase.AddTreinoUseCase
//import com.br.lealapps.domain.usecase.AddTreinoUseCaseImpl
//import com.br.lealapps.domain.usecase.DeleteExercicioUseCase
//import com.br.lealapps.domain.usecase.DeleteExercicioUseCaseImpl
//import com.br.lealapps.domain.usecase.DeleteTreinoUseCase
//import com.br.lealapps.domain.usecase.DeleteTreinoUseCaseImpl
//import com.br.lealapps.domain.usecase.GetDocRefByExerciseNameUseCase
//import com.br.lealapps.domain.usecase.GetDocRefByExerciseNameUseCaseImpl
//import com.br.lealapps.domain.usecase.GetExercicioByDocRefUseCase
//import com.br.lealapps.domain.usecase.GetExercicioByDocRefUseCaseImpl
//import com.br.lealapps.domain.usecase.GetExerciciosUseCase
//import com.br.lealapps.domain.usecase.GetExerciciosUseCaseImpl
//import com.br.lealapps.domain.usecase.GetTreinosUseCase
//import com.br.lealapps.domain.usecase.GetTreinosUseCaseImpl
//import com.br.lealapps.domain.usecase.SignInUseCase
//import com.br.lealapps.domain.usecase.SignInUseCaseImpl
//import com.br.lealapps.domain.usecase.SignUpUseCase
//import com.br.lealapps.domain.usecase.SignUpUseCaseImpl
//import com.br.lealapps.domain.usecase.UpdateExercicioUseCase
//import com.br.lealapps.domain.usecase.UpdateExercicioUseCaseImpl
//import com.br.lealapps.domain.usecase.UpdateTreinoUseCase
//import com.br.lealapps.domain.usecase.UpdateTreinoUseCaseImpl
//import dagger.Binds
//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//
//@Module
//@InstallIn(SingletonComponent::class)
//interface DomainModule {
//
//    @Binds
//    fun bindAddExercicioUseCase(
//        addExercicioUseCaseImpl: AddExercicioUseCaseImpl
//    ): AddExercicioUseCase
//
//    @Binds
//    fun bindAddTreinoUseCase(addTreinoUseCaseImpl: AddTreinoUseCaseImpl): AddTreinoUseCase
//
//    @Binds
//    fun bindDeleteExercicioUseCase(
//        deleteExercicioUseCaseImpl: DeleteExercicioUseCaseImpl
//    ): DeleteExercicioUseCase
//
//    @Binds
//    fun bindDeleteTreinoUseCase(
//        deleteTreinoUseCaseImpl: DeleteTreinoUseCaseImpl
//    ): DeleteTreinoUseCase
//
//    @Binds
//    fun bindGetDocRefByExerciseNameUseCase(
//        getDocRefByExerciseNameUseCaseImpl: GetDocRefByExerciseNameUseCaseImpl
//    ): GetDocRefByExerciseNameUseCase
//
//    @Binds
//    fun bindGetExercicioByDocRefUseCase(
//        getExercicioByDocRefUseCaseImpl: GetExercicioByDocRefUseCaseImpl
//    ): GetExercicioByDocRefUseCase
//
//    @Binds
//    fun bindGetExerciciosUseCase(
//        getExerciciosUseCaseImpl: GetExerciciosUseCaseImpl
//    ): GetExerciciosUseCase
//
//    @Binds
//    fun bindGetTreinosUseCase(getTreinosUseCaseImpl: GetTreinosUseCaseImpl): GetTreinosUseCase
//
//    @Binds
//    fun bindSignInUseCase(signInUseCaseImpl: SignInUseCaseImpl): SignInUseCase
//
//    @Binds
//    fun bindSignUpUseCase(signUpUseCaseImpl: SignUpUseCaseImpl): SignUpUseCase
//
//    @Binds
//    fun bindUpdateExercicioUseCase(
//        updateExercicioUseCaseImpl: UpdateExercicioUseCaseImpl
//    ): UpdateExercicioUseCase
//
//    @Binds
//    fun bindUpdateTreinoUseCase(
//        updateTreinoUseCaseImpl: UpdateTreinoUseCaseImpl
//    ): UpdateTreinoUseCase
//
//    @Binds
//    fun bindFitnessResponseMapper(
//        fitnessResponseMapperImpl: FitnessResponseMapperImpl
//    ): FitnessResponseMapper
//
///////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//    @Provides
//    @Singleton
//    fun provideFitnessRepository(): FitnessRepository {
//        return FirestoreFitnessRepository(provideFitnessDataSource(), provideFitnessResponseMapper())
//    }
//
//    @Provides
//    @Singleton
//    fun provideAuthRepository(): AuthRepository {
//        return FirebaseAuthRepository(provideAuthDataSource())
//    }
//
//    @Provides
//    @Singleton
//    fun provideFitnessResponseMapper(): FitnessResponseMapper {
//        return FitnessResponseMapperImpl(provideFitnessListMapper())
//    }
//
//    @Provides
//    @Singleton
//    fun provideFitnessDataSource(): FitnessDataSource {
//        return FirestoreFitnessService(providesFirestore())
//    }
//
//    @Provides
//    @Singleton
//    fun provideAuthDataSource(): AuthDataSource {
//        return FirebaseAuthService(providesFirebaseAuth())
//    }
//
//    @Provides
//    @Singleton
//    fun provideGetDocRefByExerciseNameUseCase(): GetDocRefByExerciseNameUseCase {
//        return GetDocRefByExerciseNameUseCaseImpl(provideFitnessRepository())
//    }
//
//    @Provides
//    @Singleton
//    fun provideGetExercicioByDocRefUseCase(): GetExercicioByDocRefUseCase {
//        return GetExercicioByDocRefUseCaseImpl(provideFitnessRepository())
//    }
//
//    @Provides
//    @Singleton
//    fun provideFitnessListMapper(): FitnessListMapper {
//        return FitnessListMapperImpl(
//            provideGetExercicioByDocRefUseCase(),
//            provideGetDocRefByExerciseNameUseCase()
//        )
//    }
//
//    @Provides
//    @Singleton
//    fun provideAddExercicioUseCase(): AddExercicioUseCase {
//        return AddExercicioUseCaseImpl(provideFitnessRepository())
//    }
//
//    @Provides
//    @Singleton
//    fun provideAddTreinoUseCase(): AddTreinoUseCase {
//        return AddTreinoUseCaseImpl(provideFitnessRepository())
//    }
//
//    @Provides
//    @Singleton
//    fun provideDeleteExercicioUseCase(): DeleteExercicioUseCase {
//        return DeleteExercicioUseCaseImpl(provideFitnessRepository())
//    }
//
//    @Provides
//    @Singleton
//    fun provideDeleteTreinoUseCase(): DeleteTreinoUseCase {
//        return DeleteTreinoUseCaseImpl(provideFitnessRepository())
//    }
//
//    @Provides
//    @Singleton
//    fun provideGetExerciciosUseCase(): GetExerciciosUseCase {
//        return GetExerciciosUseCaseImpl(provideFitnessRepository())
//    }
//
//    @Provides
//    @Singleton
//    fun provideGetTreinosUseCase(): GetTreinosUseCase {
//        return GetTreinosUseCaseImpl(provideFitnessRepository())
//    }
//
//    @Provides
//    @Singleton
//    fun provideSignInUseCase(): SignInUseCase {
//        return SignInUseCaseImpl(provideAuthRepository())
//    }
//
//    @Provides
//    @Singleton
//    fun provideSignUpUseCase(): SignUpUseCase {
//        return SignUpUseCaseImpl(provideAuthRepository())
//    }
//
//    @Provides
//    @Singleton
//    fun provideUpdateExercicioUseCase(): UpdateExercicioUseCase {
//        return UpdateExercicioUseCaseImpl(provideFitnessRepository())
//    }
//
//    @Provides
//    @Singleton
//    fun provideUpdateTreinoUseCase(): UpdateTreinoUseCase {
//        return UpdateTreinoUseCaseImpl(provideFitnessRepository())
//    }
//}