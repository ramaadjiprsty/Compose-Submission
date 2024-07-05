package com.example.submissioncompose.data.retrofit

import com.example.submissioncompose.data.response.AgentResponse
import com.example.submissioncompose.data.response.DetailAgent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v1/agents")
    fun getAgent(
        @Query("isPlayableCharacter") isPlayable: Boolean = true
    ): Call<AgentResponse>

    @GET("v1/agents/{uuid}")
    fun getAgentDetail(
        @Path("uuid") uuid: String
    ): Call<DetailAgent>
}