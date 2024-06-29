package com.example.submissioncompose.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.submissioncompose.data.repository.AgentRepository
import retrofit2.http.Query

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    names: List<String> = List(10) { "Hello Android #$it" },
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(AgentRepository())),
//    navigateToDetail: (Int) -> Unit,
) {
    val query by viewModel.query

    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        item {
            SearchBar(
                query = query,
                onQueryChange = viewModel::search
            )
        }
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
                Text(text = "Hello,",)
                Text(text = name)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        },
        placeholder = {
            Text(text = "Search...")
        },
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
    ) {

    }
}