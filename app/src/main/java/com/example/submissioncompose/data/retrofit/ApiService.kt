package com.example.submissioncompose.data.retrofit

import com.example.submissioncompose.data.response.AgentResponse
import com.example.submissioncompose.data.response.DataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/agents")
    fun getAgent(
        @Query("isPlayableCharacter") isPlayable: Boolean = true
    ): Call<AgentResponse>
}