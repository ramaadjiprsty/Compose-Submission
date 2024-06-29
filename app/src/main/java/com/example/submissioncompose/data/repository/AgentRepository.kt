package com.example.submissioncompose.data.repository

import androidx.compose.runtime.mutableStateOf
import com.example.submissioncompose.data.response.DataItem

class AgentRepository {

    private val _dataItems = mutableStateOf<List<DataItem>>(emptyList())
    val dataItems: List<DataItem> = _dataItems.value

    fun searchAgent(query: String): List<DataItem> {
        return _dataItems.value.filter {
            it.displayName?.contains(query, ignoreCase = true) ?: false
        }
    }
}