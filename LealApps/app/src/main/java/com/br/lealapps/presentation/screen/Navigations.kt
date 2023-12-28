package com.br.lealapps.presentation.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.br.lealapps.presentation.viewmodel.AuthViewModel
import com.br.lealapps.presentation.viewmodel.HomeViewModel

@Composable
fun AppNavigation(viewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "loginScreen") {
        composable("loginScreen") { SignInScreen(navController, viewModel) }
        composable("signUpScreen") { SignUpScreen(navController, viewModel) }
    }
}

@Composable
fun HomeNavigation(viewModel: HomeViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "homeScreen") {
        composable("homeScreen") { HomeScreen(navController, viewModel) }

        composable(
            "trainingDetailScreen/{treinoName}",
            arguments = listOf(navArgument("treinoName") { type = NavType.StringType })
        ) { backStackEntry ->
            val treinoName = backStackEntry.arguments?.getString("treinoName")
            val treino = viewModel.getTreinoByName(treinoName = treinoName ?: "")

            if (treino != null) {
                TrainingDetailScreen(navController, viewModel, treino)
            }
        }
        composable("addTrainingScreen") { AddTrainingScreen(navController, viewModel) }
        composable(
            "editTrainingScreen/{treinoName}",
            arguments = listOf(navArgument("treinoName") { type = NavType.StringType })
        ) { backStackEntry ->
            val treinoName = backStackEntry.arguments?.getString("treinoName")
            val treino = viewModel.getTreinoByName(treinoName = treinoName ?: "")

            if (treino != null) {
                EditTrainingScreen(navController, viewModel, treino)
            }
        }
        composable("exercisesScreen") { ExercisesScreen(navController, viewModel) }
        composable("addExerciseScreen") { AddExerciseScreen(navController, viewModel) }
        composable(
            "editExerciseScreen/{exercicioName}",
            arguments = listOf(navArgument("exercicioName") { type = NavType.StringType })
        ) { backStackEntry ->
            val exercicioName = backStackEntry.arguments?.getString("exercicioName")
            val exercicio = viewModel.getExercicioByName(exercicioName = exercicioName ?: "")

            if (exercicio != null) {
                EditExerciseScreen(navController, viewModel, exercicio)
            }
        }
    }
}
