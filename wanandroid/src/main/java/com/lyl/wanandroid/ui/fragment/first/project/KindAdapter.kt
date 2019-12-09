package com.lyl.wanandroid.ui.fragment.first.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lyl.wanandroid.R
import com.lyl.wanandroid.listener.OnItemClickListener

/**
 * Create By: lyl
 * Date: 2019-07-19 17:27
 */
class KindAdapter : PagedListAdapter<KindBean, KindAdapter.ProvinceViewHolder>(diffCallback) {

    var itemClickListener: OnItemClickListener<KindBean>? = null

    var kindBean: KindBean? = null

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<KindBean>() {
            override fun areItemsTheSame(oldItem: KindBean, newItem: KindBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: KindBean, newItem: KindBean): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        return ProvinceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.kind_ilist_temview, parent, false))
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        holder.bindTo(getItem(position), this!!.kindBean!!)

        holder.view.setOnClickListener {
            itemClickListener?.itemClick(getItem(position)!!, position)
        }
    }

    class ProvinceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.kind_name)
        var checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
        var view: LinearLayout = itemView.findViewById(R.id.view)

        fun bindTo(account: KindBean?, kindBean: KindBean) {
            account?.let {
                name.text = it.name
                checkBox.isChecked.run {
                    kindBean.id == it.id
                }
            }
        }
    }

    fun setSelect(kindBean: KindBean) {
        this.kindBean = kindBean
    }
}