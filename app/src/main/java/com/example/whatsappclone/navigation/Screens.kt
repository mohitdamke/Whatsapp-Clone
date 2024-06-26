package com.example.whatsappclone.navigation

sealed class Screens(val route: String) {
    object Login : Screens("login")
    object Signup : Screens("SignUp")
    object Profile : Screens("Profile")
    object ChatList : Screens("ChatList")
    object SingleChat : Screens("SingleChat/{chatId}") {
        fun createRoute(chatId: String) = "SingleChat/$chatId"
    }

    object StatusList : Screens("StatusList")
    object SingleStatus : Screens("SingleStatus/{userId}") {
        fun createRoute(userId: String) = "SingleChat/$userId"
    }


}
