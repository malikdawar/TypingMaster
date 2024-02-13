package com.example.typingmaster.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.typingmaster.activities.model.MainViewModel
import com.example.typingmaster.ui.screens.home.HomeTypingScreen
import com.example.typingmaster.ui.screens.login.UserLoginScreen
import com.example.typingmaster.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity.kt
 * @author Malik Dawar
 */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    val viewModel: MainViewModel = viewModel()

    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            // Set up the navigation graph
            NavHost(navController = navController, startDestination = "loginFragment") {
                composable("loginFragment") {
                    UserLoginScreen { userName ->
                        viewModel.setUserName(userName)
                        navController.navigate("homeFragment")
                    }
                }
                composable(
                    route = "homeFragment",
                ) {
                    HomeTypingScreen(viewModel.username.value)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        MainScreen()
    }
}
