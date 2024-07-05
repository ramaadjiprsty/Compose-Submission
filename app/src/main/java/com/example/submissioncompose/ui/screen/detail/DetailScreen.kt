package com.example.submissioncompose.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter
import com.example.submissioncompose.data.response.DetailAgent
import com.example.submissioncompose.ui.theme.blackV

@Composable
fun DetailScreen(
    agentId: String,
    viewModel: DetailViewModel,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        viewModel.clearDetail()
        viewModel.getAgentDetail(agentId)
    }

    val selectedAgents = viewModel.detailAgent.collectAsState()

    Detail(dataItem = selectedAgents, viewModel = viewModel)
}

@Composable
fun Detail(
    dataItem: State<DetailAgent.Data?>,
    viewModel: DetailViewModel,
    modifier: Modifier = Modifier
) {
    val isLoading = viewModel.loading

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize().background(blackV),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(blackV),

            ) {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.Center)
            {
                Image(
                    painter = rememberAsyncImagePainter(dataItem.value?.background ?: ""),
                    contentDescription = "",)
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(dataItem.value?.fullPortrait ?: ""),
                        contentDescription = "",
                    )
                }
            }
        }
        //Information About Agent
    }

    }
