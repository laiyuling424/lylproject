package com.lyl.wanandroid.ui.fragment.wechatpublic

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
 * Date: 2019-07-08 19:02
 */
class WeChatContentAdapter : PagedListAdapter<WeChatContentBean, WeChatContentAdapter.ProvinceViewHolder>(diffCallback) {

    var itemClickListenerTwo: OnItemClickListenerTwo<WeChatContentBean>? = null

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<WeChatContentBean>() {
            override fun areItemsTheSame(oldItem: WeChatContentBean, newItem: WeChatContentBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: WeChatContentBean, newItem: WeChatContentBean): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        return ProvinceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.wechat_content_list_itemview, parent, false))
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        holder.bindTo(getItem(position))

        holder.itemView.setOnClickListener {
            itemClickListenerTwo?.itemClickTwo(getItem(position)!!, position)
        }
    }

    class ProvinceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.name)
        val title: TextView = itemView.findViewById(R.id.title)
        val time: TextView = itemView.findViewById(R.id.time)

        fun bindTo(account: WeChatContentBean?) {
            account?.let {
                name.text=it.author
                time.text=it.niceDate
                title.text=it.title
            }
        }
    }
}