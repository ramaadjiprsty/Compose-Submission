package com.example.submissioncompose.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.submissioncompose.data.response.DetailAgent
import com.example.submissioncompose.ui.theme.blackV
import com.example.submissioncompose.ui.theme.tungstenFont

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
            modifier = Modifier
                .fillMaxSize()
                .background(blackV),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(blackV)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(dataItem.value?.background),
                    contentDescription = "",
                )
                Image(
                    painter = rememberAsyncImagePainter(dataItem.value?.fullPortrait),
                    contentDescription = "",
                )
            }
            Text(
                text = dataItem.value?.displayName?.uppercase() ?: "",
                fontSize = 32.sp,
                letterSpacing = 1.sp,
                fontFamily = tungstenFont,
                color = Color.White
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = dataItem.value?.description ?: "",
                fontSize = 16.sp,
                letterSpacing = 1.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "ABILITIES",
                fontSize = 32.sp,
                letterSpacing = 1.sp,
                fontFamily = tungstenFont,
                color = Color.White
            )
            dataItem.value?.abilities?.forEach {
                Row(modifier = Modifier.padding(vertical = 8.dp)) {
                    Image(
                        painter = rememberAsyncImagePainter(model = it?.displayIcon),
                        contentDescription = "",
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .height(60.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = it?.description ?:"",
                        fontSize = 16.sp,
                        letterSpacing = 1.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

