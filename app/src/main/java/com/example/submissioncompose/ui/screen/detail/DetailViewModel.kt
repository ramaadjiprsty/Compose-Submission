package com.example.submissioncompose.ui.screen.detail

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissioncompose.data.response.AgentResponse
import com.example.submissioncompose.data.response.DataItem
import com.example.submissioncompose.data.response.DetailAgent
import com.example.submissioncompose.data.retrofit.ApiConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {

    private val _detailAgent : MutableStateFlow<DetailAgent.Data?> = MutableStateFlow(null)
    val detailAgent : StateFlow<DetailAgent.Data?> = _detailAgent

    var loading : Boolean by mutableStateOf(false)

    fun getAgentDetail(agentUuid: String) {
        viewModelScope.launch {
            loading = true
            delay(1000)
            val client : Call<DetailAgent> = ApiConfig.getApiService().getAgentDetail(agentUuid)
            client.enqueue(object : Callback<DetailAgent> {
                override fun onResponse(
                    call: Call<DetailAgent>,
                    response: Response<DetailAgent>
                ) {
                    if (response.isSuccessful && response.code() == 200) {
                        loading = true
                        val responseData: DetailAgent? = response.body()
                        if (responseData != null) {
                            _detailAgent.value = responseData.data
                            }
                        loading = false
                        }
                    }

                override fun onFailure(call: Call<DetailAgent>, t: Throwable) {
                   loading = false
                    Log.d("Failed Retrieve", "Error Bang")
                }

            })
        }
    }

    fun clearDetail() {
        _detailAgent.value = null
    }
}