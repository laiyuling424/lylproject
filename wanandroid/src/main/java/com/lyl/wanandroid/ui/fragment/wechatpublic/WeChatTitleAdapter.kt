package com.lyl.wanandroid.ui.fragment.wechatpublic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lyl.wanandroid.R
import com.lyl.wanandroid.listener.OnItemClickListener

/**
 * Create By: lyl
 * Date: 2019-07-08 14:51
 */
class WeChatTitleAdapter : PagedListAdapter<WeChatPublicListBean, WeChatTitleAdapter.ProvinceViewHolder>(diffCallback) {

    var itemClickListener: OnItemClickListener<WeChatPublicListBean>? = null

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<WeChatPublicListBean>() {
            override fun areItemsTheSame(oldItem: WeChatPublicListBean, newItem: WeChatPublicListBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: WeChatPublicListBean, newItem: WeChatPublicListBean): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        return ProvinceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.wechat_list_itemview, parent, false))
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        holder.bindTo(getItem(position))

        holder.itemView.setOnClickListener {
            itemClickListener?.itemClick(getItem(position)!!, position)
        }
    }

    class ProvinceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tv: TextView = itemView.findViewById(R.id.wechat_title_name)

        fun bindTo(account: WeChatPublicListBean?) {
            account?.let {
                tv.text = it.name
            }
        }
    }
}