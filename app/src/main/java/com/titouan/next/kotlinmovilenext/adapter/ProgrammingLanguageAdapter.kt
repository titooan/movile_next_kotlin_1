package com.titouan.next.kotlinmovilenext.adapter

import android.content.Context
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.titouan.next.kotlinmovilenext.R
import com.titouan.next.kotlinmovilenext.model.ProgrammingLanguage
import kotlinx.android.synthetic.main.programming_language_item.view.*

class ProgrammingLanguageAdapter(private val items: List<ProgrammingLanguage>,
                                 private val context: Context,
                                 private val listener: (ProgrammingLanguage) -> Unit
) : Adapter<ProgrammingLanguageAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.programming_language_item, parent, false))


    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position], listener)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(programmingLanguage: ProgrammingLanguage,
                     listener: (ProgrammingLanguage) -> Unit) = with(itemView) {

            if (programmingLanguage.imageResourceId != 0) {
                ivMain.setImageResource(programmingLanguage.imageResourceId)
            } else {
                ivMain.setImageResource(R.drawable.ic_developer_board)
            }

            tvTitle.text = programmingLanguage.title
            tvLaunchYear.text = context.getString(R.string.programming_language_year, programmingLanguage.year)
            tvDescription.setText(programmingLanguage.description)

            itemView.setOnClickListener {
                listener(programmingLanguage)
            }
        }
    }

}