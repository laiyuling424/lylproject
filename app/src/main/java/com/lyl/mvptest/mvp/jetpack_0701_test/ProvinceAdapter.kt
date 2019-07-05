package com.lyl.mvptest.mvp.jetpack_0701_test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lyl.mvptest.R

/**
 * User: lyl
 * Date: 2019-07-01 16:10
 */
class ProvinceAdapter : PagedListAdapter<ProvinceBean, ProvinceAdapter.ProvinceViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<ProvinceBean>() {
            override fun areItemsTheSame(oldItem: ProvinceBean, newItem: ProvinceBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ProvinceBean, newItem: ProvinceBean): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        return ProvinceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.province_itemview, parent, false))
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class ProvinceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tv: TextView = itemView.findViewById(R.id.textview)

        fun bindTo(account: ProvinceBean?) {
            account?.let {
                tv.text = it.id.toString() + it.name
            }
        }
    }
}