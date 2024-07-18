package com.example.mobileproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.mobileproject.data.SportifyDatabase
import com.example.mobileproject.data.UserViewModelFactory
import com.example.mobileproject.presentation.AddFieldScreen
import com.example.mobileproject.presentation.FieldScreen
import com.example.mobileproject.presentation.FieldsViewModel
import com.example.mobileproject.presentation.HomepageScreen
import com.example.mobileproject.presentation.ReservationsViewModel
import com.example.mobileproject.presentation.ReservesPageScreen
import com.example.mobileproject.presentation.UserViewModel
import com.example.mobileproject.screen.RegistrationScreen
import com.example.mobileproject.ui.theme.MobileprojectTheme
import com.example.project.screen.LoginScreen

class MainActivity : ComponentActivity() {

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            SportifyDatabase::class.java,
            "app_database"
        ).build()
    }

    private val viewModel by viewModels<FieldsViewModel> (
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun<T: ViewModel> create(modelClass: Class<T>): T {
                    return FieldsViewModel(database.dao) as T
                }
            }
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileprojectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state by viewModel.state.collectAsState()
                    val navController = rememberNavController()
                    val userDao = database.userDao
                    val userViewModel: UserViewModel =
                        viewModel(factory = UserViewModelFactory(userDao))


                    NavHost(navController = navController, startDestination = "LoginScreen") {
                        composable("LoginScreen") {
                            LoginScreen(
                                userViewModel = userViewModel,
                                navController = navController
                            )
                        }
                        composable("RegistrationScreen") {
                            RegistrationScreen(
                                userViewModel = userViewModel,
                                navController = navController
                            )
                        }
                        composable("HomepageScreen/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                            if (id !=null){
                                HomepageScreen(
                                    state = state,
                                    id = id,
                                    navController = navController,
                                    onEvent = viewModel::onEvent
                                )
                            }
                            else{
                                navController.navigate("login")
                            }
                        }
                        composable("AddFieldScreen"){
                            AddFieldScreen(
                                state = state,
                                navController = navController,
                                onEvent = viewModel::onEvent
                            )
                        }
                        composable("FieldScreen/{fieldId}/{userId}") { backStackEntry ->
                            val fieldId = backStackEntry.arguments?.getString("fieldId")?.toIntOrNull()
                            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
                            if (fieldId != null) {
                                FieldScreen(
                                    state = state,
                                    index = fieldId,
                                    userId = userId,
                                    navController = navController,
                                    onEvent = viewModel::onEvent,
                                )
                            } else {
                                // Handle invalid or missing fieldId
                                // You can navigate to an error screen or perform other actions
                                // For now, let's navigate back to the homepage
                                navController.navigate("HomepageScreen/{userId}") {
                                    popUpTo("HomepageScreen/{userId}") { inclusive = true }
                                }
                            }
                        }
                        composable("ReservesPageScreen/{id}") { backStackEntry ->
                            val userId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                            if (userId != null) {
                                ReservesPageScreen(
                                    userId =  userId,
                                    navController = navController,
                                )
                            } else {
                                // Handle invalid or missing fieldId
                                // You can navigate to an error screen or perform other actions
                                // For now, let's navigate back to the homepage
                                navController.navigate("HomepageScreen/{userId}") {
                                    popUpTo("HomepageScreen/{userId}") { inclusive = true }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

