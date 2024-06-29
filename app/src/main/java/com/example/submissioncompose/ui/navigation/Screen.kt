package com.example.submissioncompose.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Detail : Screen("detail")
    data object About : Screen("about")
}