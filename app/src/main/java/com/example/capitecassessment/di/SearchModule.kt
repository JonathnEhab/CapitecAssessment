package com.example.capitecassessment.di

import com.example.capitecassessment.api.GithubApiService
import com.example.capitecassessment.api.GithubSearchApi
import com.example.capitecassessment.presenation.ui.home.repo.GithubRepository
import com.example.capitecassessment.presenation.ui.home.repo.GithubSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {
    @Provides
    fun provideSearchRepository(apiSearchApi: GithubSearchApi): GithubSearchRepository {
        return GithubSearchRepository(apiSearchApi)
    }
}