package com.example.whatsappclone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whatsappclone.screen.ChatListScreen
import com.example.whatsappclone.screen.LoginScreen
import com.example.whatsappclone.screen.SignUpScreen
import com.example.whatsappclone.screen.SplashScreen


@Composable
fun NavigationScreens() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.Splash.route) {

        composable(Screens.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Screens.Signup.route) {
            SignUpScreen(navController = navController)
        }
        composable(Screens.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screens.ChatList.route) {
            ChatListScreen(navController = navController)
        }


    }
}