package com.example.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo_data")
data class GitHubResponse(
    val incomplete_results: Boolean,
    val items: List<Item>,
    @PrimaryKey
    val total_count: Int
)