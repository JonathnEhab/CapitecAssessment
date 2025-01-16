package com.example.capitecassessment.api

import com.example.capitecassessment.domain.repository.repositories
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiService {
    @GET("users/jonathn/repos")
    suspend fun getRepos(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 5
    ): repositories

}