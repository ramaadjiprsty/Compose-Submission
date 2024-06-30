package com.example.submissioncompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.submissioncompose.ui.navigation.Screen
import com.example.submissioncompose.ui.screen.about.AboutScreen
import com.example.submissioncompose.ui.screen.home.HomeScreen
import com.example.submissioncompose.ui.screen.home.HomeViewModel
import com.example.submissioncompose.ui.theme.blackV
import com.example.submissioncompose.ui.theme.redV
import com.example.submissioncompose.ui.theme.valorantFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValorantAgentApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var actionBarTitle by remember { mutableStateOf("valorant") }
    val viewModel: HomeViewModel = viewModel()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = actionBarTitle,
                        fontFamily = valorantFont,
                        color = redV,
                        fontSize = 28.sp
                        )
                        },
                colors = TopAppBarDefaults.topAppBarColors(blackV),
                actions = {
                    IconButton(onClick = {} ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            tint = redV
                        )
                    }
                    IconButton(onClick = {
                        navController.navigate(Screen.About.route) {
                            launchSingleTop = true
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "About",
                            tint = redV
                        )
                    }
                },
                navigationIcon = {
                    if (navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = redV
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screen.Home.route) {
                HomeScreen(viewModel = viewModel)
                actionBarTitle = "Valorant"
            }
            composable(Screen.About.route) {
                AboutScreen()
                actionBarTitle = "About"
            }
        }

    }
}