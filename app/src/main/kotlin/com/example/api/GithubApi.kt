package com.example.api

import com.example.data.response.Repo
import retrofit2.http.GET

interface GithubApi {

    @GET("users/patrick-fitzgerald/repos")
    suspend fun repos(): List<Repo>
}
