package com.titouan.next.kotlinmovilenext.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.titouan.next.kotlinmovilenext.R
import com.titouan.next.kotlinmovilenext.api.Repository
import kotlinx.android.synthetic.main.repository_item.view.*

class RepositoryAdapter(private val items: List<Repository>,
                        private val context: Context,
                        private val listener: (Repository) -> Unit
) : Adapter<RepositoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.repository_item, parent, false))


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position], listener)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(repository: Repository,
                     listener: (Repository) -> Unit) = with(itemView) {

            tvTitle.text = repository.name
            tvOwner.text = repository.owner?.login ?: "Unknown"

            itemView.setOnClickListener {
                listener(repository)
            }

            Glide.with(this)
                    .load(repository.owner?.avatar_url)
                    .into(ivMain)
        }
    }

}