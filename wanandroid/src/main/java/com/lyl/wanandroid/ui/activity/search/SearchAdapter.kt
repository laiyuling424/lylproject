package com.lyl.wanandroid.ui.activity.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lyl.wanandroid.R
import com.lyl.wanandroid.listener.OnItemClickListenerTwo

/**
 * Create By: lyl
 * Date: 2019-07-12 15:29
 */
class SearchAdapter : PagedListAdapter<SearchResponseBean, SearchAdapter.ProvinceViewHolder>(diffCallback) {

    var itemClickListener: OnItemClickListenerTwo<SearchResponseBean>? = null

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<SearchResponseBean>() {
            override fun areItemsTheSame(oldItem: SearchResponseBean, newItem: SearchResponseBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SearchResponseBean, newItem: SearchResponseBean): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        return ProvinceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.mainarticle_list_itemview, parent, false))
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        holder.bindTo(getItem(position))

        holder.itemView.setOnClickListener {
            itemClickListener?.itemClickTwo(getItem(position)!!, position)
        }
    }

    class ProvinceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.name)
        val title: TextView = itemView.findViewById(R.id.title)
        val time: TextView = itemView.findViewById(R.id.time)
        val classification: TextView = itemView.findViewById(R.id.classification)

        fun bindTo(account: SearchResponseBean?) {
            account?.let {
                name.text = it.author
                time.text = it.niceDate
                title.text = it.title
                classification.text = it.superChapterName + "/" + it.chapterName
            }
        }
    }
}