package com.example.capitecassessment.presenation.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capitecassessment.domain.repository.repositories
import com.example.capitecassessment.domain.repository.repositoriesItem
import com.example.capitecassessment.presenation.ui.home.repo.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    private val _repos = MutableLiveData<List<repositoriesItem>>(emptyList())
    val repos: LiveData<List<repositoriesItem>> get() = _repos

    fun fetchUserRepos(page: Int, perPage: Int) {
        viewModelScope.launch {
            try {
                val result = repository.getUserRepos(page, perPage)

                val currentRepos = _repos.value.orEmpty() + result
                _repos.value = currentRepos
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}


