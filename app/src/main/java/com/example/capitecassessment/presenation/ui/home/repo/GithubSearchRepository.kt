package com.example.capitecassessment.presenation.ui.home.repo

import android.util.Log
import com.example.capitecassessment.api.GithubApiService
import com.example.capitecassessment.api.GithubSearchApi
import com.example.capitecassessment.domain.repository.repositories
import com.example.capitecassessment.domain.repository.repositoriesItem
import com.example.capitecassessment.domain.search.Item
import javax.inject.Inject
import javax.inject.Singleton


class GithubSearchRepository @Inject constructor(
    private val apiService: GithubSearchApi
) {
    suspend fun getUserRepos(q: String, page: Int, perPage: Int): List<Item> {
        val response = apiService.searchRepositories(q, page, perPage)
        return response.items
    }
}


