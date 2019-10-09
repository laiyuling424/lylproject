package com.lyl.wanandroid.ui.activity.collectweb

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
import com.lyl.wanandroid.ui.bean.CollectWebBean

/**
 * Create By: lyl
 * Date: 2019-08-02 15:48
 */
class CollectWebAdapter : PagedListAdapter<CollectWebBean, CollectWebAdapter.CollectWebViewHoder>(diffCallback) {

    var itemClickListener: OnItemClickListener<CollectWebBean>? = null

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<CollectWebBean>() {
            override fun areItemsTheSame(oldItem: CollectWebBean, newItem: CollectWebBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CollectWebBean, newItem: CollectWebBean): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectWebViewHoder {
        return CollectWebViewHoder(LayoutInflater.from(parent.context).inflate(R.layout.collectweb_list_itemview,null))
    }

    override fun onBindViewHolder(holder: CollectWebViewHoder, position: Int) {
        holder.bindTo(getItem(position))

        holder.itemView.setOnClickListener {
            itemClickListener?.itemClick(getItem(position)!!, position)
        }
    }

    class CollectWebViewHoder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val name: TextView = itemView.findViewById(R.id.name)

        fun bindTo(account: CollectWebBean?) {
            account?.let {
                name.text = it.name

            }
        }
    }
}