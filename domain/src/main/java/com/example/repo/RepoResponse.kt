package com.example.repo

import com.example.domain.entity.GitHubResponse
import com.example.domain.entity.Item

interface RepoResponse {
    suspend fun getResponse(q :String ,page: Int, perPage: Int): GitHubResponse
}