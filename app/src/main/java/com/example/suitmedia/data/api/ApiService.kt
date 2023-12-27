package com.example.suitmedia.data.api

import com.example.suitmedia.data.response.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUser(
        @Query("page") page: Int
    ): Response

}