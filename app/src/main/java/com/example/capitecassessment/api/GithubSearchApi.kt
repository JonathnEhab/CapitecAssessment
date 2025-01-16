package com.example.capitecassessment.api

import com.example.capitecassessment.domain.repository.repositories
import com.example.capitecassessment.domain.search.Item
import com.example.capitecassessment.domain.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubSearchApi {
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): SearchResponse
}

