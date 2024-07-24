package com.example.submissioncompose.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.submissioncompose.data.response.DataItem
import com.example.submissioncompose.ui.theme.blackV
import com.example.submissioncompose.ui.theme.blueV
import com.example.submissioncompose.ui.theme.tungstenFont
import com.example.submissioncompose.ui.theme.valorantFont

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
//    navigateToDetail: (Int) -> Unit,
    navHostController: NavHostController,
    navigateToDetail: (String) -> Unit
) {
    val agentsData = viewModel.agentsData.collectAsState()
    LazyColumn(
        modifier = Modifier.background(blackV)
    ) {
        items(count = agentsData.value.size) {
            AgentItem(
                dataItem = agentsData.value[it],
                navHostController = navHostController,
                navigateToDetail = navigateToDetail)
        }
    }
}

@Composable
fun AgentItem(
    dataItem: DataItem,
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    navigateToDetail: (String) -> Unit,
) {
    Surface(
        color = blueV,
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                navigateToDetail(dataItem.uuid!!)
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        ) {
            Row {
                Image(
                    painter = rememberAsyncImagePainter(dataItem.fullPortrait),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(180.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(top = 24.dp)
                ) {
                    Row(modifier = Modifier.align(Alignment.End)) {
                        Text(
                            text = dataItem.role?.displayName!!.uppercase(),
                            fontFamily = valorantFont,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            letterSpacing = 1.sp,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                        )
                        Image(
                            painter = rememberAsyncImagePainter(dataItem.role.displayIcon),
                            contentDescription = "",
                            modifier
                                .padding(start = 8.dp)
                                .size(17.dp)
                        )
                    }

                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = dataItem.displayName!!.uppercase(),
                    color = Color.White,
                    fontSize = 50.sp,
                    fontFamily = tungstenFont,
                    letterSpacing = 8.sp
                )
            }
        }
    }
}