package com.example.capitecassessment.presenation.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capitecassessment.databinding.ItemRepoBinding
import com.example.capitecassessment.domain.search.Item

class SearchAdapter : ListAdapter<Item, SearchAdapter.ViewHolder>(RepoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val itemBinding: ItemRepoBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(repo: Item) {

            itemBinding.repoName.text = repo.name
            itemBinding.repoDescription.text = repo.description ?: "No description available"
            itemBinding.repoLink.text = repo.url
            Glide.with(itemBinding.root.context)
                .load(repo.owner.avatar_url)
                .into(itemBinding.avatarImage)
        }
    }

    class RepoDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}
