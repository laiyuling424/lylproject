package com.lyl.wanandroid.ui.test_jetpack.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lyl.wanandroid.R

/**
 * User: lyl
 * Date: 2019-06-13 11:38
 */

class ByItemAdapter : PagedListAdapter<GithubAccount, ByItemAdapter.ByItemViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<GithubAccount>() {
            override fun areItemsTheSame(oldItem: GithubAccount, newItem: GithubAccount): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GithubAccount, newItem: GithubAccount): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ByItemViewHolder {
        return ByItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.by_item_rec_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: ByItemViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class ByItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mId: TextView = itemView.findViewById(R.id.id)
        private val mName: TextView = itemView.findViewById(R.id.name)

        fun bindTo(account: GithubAccount?) {
            account?.let {
                mId.text = it.id.toString()
                mName.text = it.login
            }
        }
    }
}