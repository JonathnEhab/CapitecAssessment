package com.example.capitecassessment.presenation.ui.home.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capitecassessment.databinding.FragmentSearchBinding
import com.example.capitecassessment.presenation.ui.home.adapter.RepoAdapter
import com.example.capitecassessment.presenation.ui.home.adapter.SearchAdapter
import com.example.capitecassessment.presenation.ui.home.viewmodel.GithubSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class SearchFragment  : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GithubSearchViewModel by viewModels()
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SearchAdapter()
        binding.repoRecycler.adapter = adapter
        binding.repoRecycler.layoutManager = LinearLayoutManager(requireContext())


        viewModel.repos.observe(viewLifecycleOwner) { repos ->
            adapter.submitList(repos)
        }

        binding.button.setOnClickListener {
            val query = binding.searchtext.text.toString().trim()
            if (query.isNotEmpty()) {
                Log.d("SearchFragment", "Search query: $query")
                viewModel.setRepoName(query)
            }
        }


        binding.repoRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!viewModel.isLoading.value && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                    viewModel.loadMoreRepos()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}