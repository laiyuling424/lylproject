package com.lyl.wanandroid.ui.fragment.first.tixi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lyl.wanandroid.Constants
import com.lyl.wanandroid.R
import com.lyl.wanandroid.listener.OnItemClickListener
import com.lyl.wanandroid.ui.activity.tixi_detail.TixiDetailActivity
import com.lyl.wanandroid.widget.RecycleListView2

/**
 * Create By: lyl
 * Date: 2019-07-13 09:05
 */
class TixiAdapter(context: Context) : PagedListAdapter<TixiBean, TixiAdapter.ProvinceViewHolder>(diffCallback), OnItemClickListener<Any> {

    var context: Context? = null

    init {
        this.context = context
    }

    override fun itemClick(t: Any, position: Int) {
        val intent = Intent()

        intent.setClass(context, TixiDetailActivity::class.java)

        intent.putExtra(Constants.CONTENT_Id, (t as TixiChildBean).id)

        intent.putExtra(Constants.CONTENT_TITLE, (t as TixiChildBean).name)

        context!!.startActivity(intent)
    }


    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<TixiBean>() {
            override fun areItemsTheSame(oldItem: TixiBean, newItem: TixiBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TixiBean, newItem: TixiBean): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        return ProvinceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tixi_list_itemview, parent, false))
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        holder.bindTo(getItem(position))
        holder.mRecycleListView.onItemClickListener = this
    }

    class ProvinceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.name)
        val mRecycleListView: RecycleListView2 = itemView.findViewById(R.id.recycle_list_view)

        fun bindTo(tixiBean: TixiBean?) {
            tixiBean?.let {
                name.text = it.name
//                var childredList: ArrayList<String>? = ArrayList()
//                for (i in 0 until tixiBean.children!!.size) {
//                    childredList!!.add(tixiBean.children!![i].name!!)
//                }
//                mRecycleListView.setListText(childredList)
                mRecycleListView.setListBean(tixiBean.children)
            }
        }
    }
}