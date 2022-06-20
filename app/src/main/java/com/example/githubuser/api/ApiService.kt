package com.example.githubuser.api

import com.example.githubuser.GithubUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUserSearch(
        @Query("q") query: String
    ): Call<GithubUserResponse>
}