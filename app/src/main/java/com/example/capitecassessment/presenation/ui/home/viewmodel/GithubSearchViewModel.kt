package com.example.capitecassessment.presenation.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.capitecassessment.domain.search.Item
import com.example.capitecassessment.presenation.ui.home.repo.GithubSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubSearchViewModel @Inject constructor(
    private val repository: GithubSearchRepository
) : ViewModel() {

    private val _repoName = MutableStateFlow("")


    private val _repos = MutableLiveData<List<Item>>(emptyList())
    val repos: LiveData<List<Item>> get() = _repos

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var currentPage = 1

    init {
        _repoName
            .debounce(500)
            .distinctUntilChanged()
            .filter { it.isNotBlank() }
            .onEach { query ->
                Log.d("GithubSearchViewModel", "Search triggered for query: $query")
                currentPage = 1
                searchForRepo(query, currentPage, 5, clearList = true)
            }
            .launchIn(viewModelScope)
    }

    fun setRepoName(name: String) {
        _repoName.value = name

    }

    fun loadMoreRepos() {
        if (!_isLoading.value && _repoName.value.isNotBlank()) {
            currentPage++

            searchForRepo(_repoName.value, currentPage, 5, clearList = false)
        }
    }

    private fun searchForRepo(query: String, page: Int, perPage: Int, clearList: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.emit(true)

                val result = repository.getUserRepos(query, page, perPage)


                val updatedRepos = if (clearList) result else _repos.value.orEmpty() + result
                _repos.postValue(updatedRepos)
            } catch (e: Exception) {

            } finally {
                _isLoading.emit(false)

            }
        }
    }
}

