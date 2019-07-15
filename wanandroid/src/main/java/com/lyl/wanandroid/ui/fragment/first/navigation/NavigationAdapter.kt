package com.lyl.wanandroid.ui.fragment.first.navigation

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
import com.lyl.wanandroid.ui.activity.WebViewDetailActivity
import com.lyl.wanandroid.ui.activity.tixi_detail.TixiDetailActivity
import com.lyl.wanandroid.ui.fragment.first.tixi.NavigationBean
import com.lyl.wanandroid.ui.fragment.first.tixi.NavigationListBean
import com.lyl.wanandroid.widget.RecycleListView2

/**
 * User: lyl
 * Date: 2019-07-13 09:05
 */
class NavigationAdapter(context: Context) : PagedListAdapter<NavigationListBean, NavigationAdapter.ProvinceViewHolder>(diffCallback),OnItemClickListener<Any> {

    var context:Context?=null

    init {
        this.context=context
    }

    override fun itemClick(t: Any, position: Int) {
        val intent = Intent()

        intent.setClass(context,WebViewDetailActivity::class.java)

        intent.putExtra(Constants.CONTENT_URL, (t as NavigationBean).link)

        intent.putExtra(Constants.CONTENT_TITLE, (t as NavigationBean).title)

        context!!.startActivity(intent)
    }


    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<NavigationListBean>() {
            override fun areItemsTheSame(oldItem: NavigationListBean, newItem: NavigationListBean): Boolean {
                return oldItem.cid == newItem.cid
            }

            override fun areContentsTheSame(oldItem: NavigationListBean, newItem: NavigationListBean): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceViewHolder {
        return ProvinceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tixi_list_itemview, parent, false))
    }

    override fun onBindViewHolder(holder: ProvinceViewHolder, position: Int) {
        holder.bindTo(getItem(position))
        holder.mRecycleListView.onItemClickListener=this
    }

    class ProvinceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.name)
        val mRecycleListView: RecycleListView2 = itemView.findViewById(R.id.recycle_list_view)

        fun bindTo(navigationListBean: NavigationListBean?) {
            navigationListBean?.let {
                name.text = it.name
                mRecycleListView.setListBean(navigationListBean.articles)
            }
        }
    }
}