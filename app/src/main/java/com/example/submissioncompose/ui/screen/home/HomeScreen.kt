package com.example.submissioncompose.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.example.submissioncompose.Greeting

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    names: List<String> = List(10) { "Hello Android #$it" },
//    navigateToDetail: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        items(names.size) {
            AgentList(name = names[it], modifier)
        }
    }
}

@Composable
fun AgentList(
    name: String,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Row(modifier = Modifier.padding(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 8.dp)
            ) {
                Text(text = "Hello, ")
                Text(text = name)
            }
        }
    }
}