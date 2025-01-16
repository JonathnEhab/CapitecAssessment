package com.example.data.online


import com.example.domain.entity.GitHubResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoApiService {
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): GitHubResponse

}






