package com.example.capitecassessment.domain.search

data class SearchResponse(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)