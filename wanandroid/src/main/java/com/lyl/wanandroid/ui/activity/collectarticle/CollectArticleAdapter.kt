package com.lyl.wanandroid.ui.activity.collectarticle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lyl.wanandroid.R
import com.lyl.wanandroid.listener.OnItemClickListener
import com.lyl.wanandroid.ui.bean.CollectArticleBean


/**
 * Create By: lyl
 * Date: 2019-08-02 14:27
 */
class CollectArticleAdapter : PagedListAdapter<CollectArticleBean, CollectArticleAdapter.CollectArticleViewHodel>(diffCallback) {

    var itemClickListener: OnItemClickListener<CollectArticleBean>? = null

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<CollectArticleBean>() {
            override fun areItemsTheSame(oldItem: CollectArticleBean, newItem: CollectArticleBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CollectArticleBean, newItem: CollectArticleBean): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectArticleViewHodel {
        return CollectArticleViewHodel(LayoutInflater.from(parent.context).inflate(R.layout.mainarticle_list_itemview, parent, false))
    }

    override fun onBindViewHolder(holder: CollectArticleViewHodel, position: Int) {
        holder.bindTo(getItem(position))

        holder.itemView.setOnClickListener {
            itemClickListener?.itemClick(getItem(position)!!, position)
        }
    }

    class CollectArticleViewHodel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val title: TextView = itemView.findViewById(R.id.title)
        val time: TextView = itemView.findViewById(R.id.time)
        val classification: TextView = itemView.findViewById(R.id.classification)

        fun bindTo(account: CollectArticleBean?) {
            account?.let {
                name.text = it.author
                time.text = it.niceDate
                title.text = it.title
                classification.text = it.chapterName
            }
        }
    }
}