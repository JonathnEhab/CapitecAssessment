package com.example.capitecassessment.presenation.ui.home.repo

import com.example.capitecassessment.api.GithubApiService
import com.example.capitecassessment.domain.repository.repositories
import javax.inject.Inject
import javax.inject.Singleton


class GithubRepository @Inject constructor(
    private val apiService: GithubApiService
) {
    suspend fun getUserRepos( page: Int, perPage: Int): repositories {
        return apiService.getRepos( page, perPage)
    }
}
