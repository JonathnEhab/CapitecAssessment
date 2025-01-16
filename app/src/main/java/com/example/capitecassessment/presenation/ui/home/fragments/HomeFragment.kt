package com.example.capitecassessment.presenation.ui.home.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capitecassessment.databinding.FragmentHomeBinding
import com.example.capitecassessment.presenation.ui.home.adapter.RepoAdapter
import com.example.capitecassessment.presenation.ui.home.viewmodel.GithubViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GithubViewModel by viewModels()
    private lateinit var adapter: RepoAdapter
    private var currentPage = 1
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchBadge.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
        }

        adapter = RepoAdapter()
        binding.repoRecycler.adapter = adapter
        binding.repoRecycler.layoutManager = LinearLayoutManager(requireContext())


        viewModel.fetchUserRepos(currentPage, 5)
        viewModel.repos.observe(viewLifecycleOwner) { repos ->
            if (repos != null) {

                adapter.submitList(repos)
                isLoading = false
                Log.d("TAG", "Length${repos.size}")

            }
            if (repos.size == 27) {
                isLoading = true
            } else {
                Log.d("TAG", "No data available")
            }
        }

        binding.repoRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()


                if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                    currentPage++
                    isLoading = true
                    viewModel.fetchUserRepos(currentPage, 5)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


