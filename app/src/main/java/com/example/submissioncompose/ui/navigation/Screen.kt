package com.example.submissioncompose.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Detail : Screen("detail/{agentUuid}") {
        fun createRoute(agentUuid: String) = "detail/$agentUuid"
    }
    data object About : Screen("about")
}