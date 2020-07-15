package com.example.repository

import com.example.api.GithubApi
import com.example.api.Resource
import com.example.data.response.Repo

class GithubRepository constructor(
    private val githubApi: GithubApi
) {

    suspend fun getRepos(): Resource<List<Repo>> {
        return try {
            val response = githubApi.repos()
            Resource.success(data = response)
        } catch (e: Exception) {
            Resource.error(msg = e.message ?: "An error occurred", data = null)
        }
    }
}
