package com.example.testandroid.data.network

import retrofit2.http.GET

interface ActivityApiService {
    @GET("activity")
    suspend fun getActivity(): ActivityResponse
}