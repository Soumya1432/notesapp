package com.example.notesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.notesapp.layout.BaseLayout
import com.example.notesapp.layout.DrawerWrapperLayout

import com.example.notesapp.screen.HomeScreen
import com.example.notesapp.screen.OtpScreen
import com.example.notesapp.screen.ProfileScreen
import com.example.notesapp.screen.RegisterScreen
import com.example.notesapp.screen.SigninScreen
import com.example.notesapp.screen.SignupScreen
import com.example.notesapp.screen.SplashScreen
import com.example.notesapp.screen.TodoListScreen
import com.example.notesapp.screen.TodoScreen
import com.example.notesapp.screen.VerifyOtp

@Composable
fun AppNavigation(){
    var navController = rememberNavController()
    NavHost(navController=navController, startDestination = "splash") {

        composable("splash"){
//            SignupScreen(navController)
            SplashScreen(navController)
        }


        composable("signup"){
//            SignupScreen(navController)
            RegisterScreen(navController)
        }

        composable("signin"){
//            SignupScreen(navController)
            SigninScreen(navController)
        }

        composable("otp/{email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            VerifyOtp(navController, email)
        }



        composable("home"){
            DrawerWrapperLayout( navController=navController) {
                HomeScreen(navController)
            }
        }


        composable("todo"){
            DrawerWrapperLayout( navController=navController) {
                TodoScreen(navController)
            }


        }
        composable("profile"){
            DrawerWrapperLayout( navController=navController) {
                ProfileScreen(navController)
            }


        }

    }
}