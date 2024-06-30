package com.example.submissioncompose.ui.screen.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissioncompose.data.response.AgentResponse
import com.example.submissioncompose.data.response.DataItem
import com.example.submissioncompose.data.retrofit.ApiConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    private val _agentsData : MutableStateFlow<List<DataItem>> = MutableStateFlow(listOf())
    val agentsData : StateFlow<List<DataItem>> = _agentsData

    var loading: Boolean by mutableStateOf(false)

    init {
        getAgentsData()
    }

    private fun getAgentsData() {
        viewModelScope.launch {
            loading = true
            val client : Call<AgentResponse> = ApiConfig.getApiService().getAgent()
            client.enqueue(object : Callback<AgentResponse> {
                override fun onResponse(
                    call: Call<AgentResponse>,
                    response: Response<AgentResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseData: List<DataItem>? = response.body()?.data
                        if (responseData != null) {
                            _agentsData.value = responseData.filter { dataItem ->
                                dataItem.displayName != null
                            }
                            loading = false
                        }
                    }
                }

                override fun onFailure(call: Call<AgentResponse>, t: Throwable) {
                    loading = false
                    Log.d("Failed Retrieve", "Oops Something Wrong")
                }

            })
        }
    }
}