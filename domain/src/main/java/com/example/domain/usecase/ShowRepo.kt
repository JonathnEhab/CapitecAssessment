package com.example.domain.usecase

import com.example.domain.entity.GitHubResponse
import com.example.repo.RepoResponse

class ShowRepo(private val resRepo: RepoResponse) {
    suspend operator fun invoke(q : String,page :Int, perPage :Int): GitHubResponse =resRepo.getResponse(q,page,perPage)

}

