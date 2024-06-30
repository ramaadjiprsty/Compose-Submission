package com.example.submissioncompose.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.submissioncompose.ui.component.AgentItem
import com.example.submissioncompose.ui.theme.blackV

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
//    navigateToDetail: (Int) -> Unit,
) {
    val agentsData = viewModel.agentsData.collectAsState()
    LazyColumn(
        modifier = Modifier.background(blackV)
    ) {
        items(count = agentsData.value.size) {
            AgentItem(dataItem = agentsData.value[it])
        }
    }
}