package com.example.capitecassessment.di

import com.example.capitecassessment.api.GithubApiService
import com.example.capitecassessment.presenation.ui.home.repo.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun provideRepository(apiService: GithubApiService): GithubRepository {
        return GithubRepository(apiService)
    }
}