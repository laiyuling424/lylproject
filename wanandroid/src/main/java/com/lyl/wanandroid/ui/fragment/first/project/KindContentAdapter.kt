package com.lyl.wanandroid.ui.fragment.first.project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lyl.wanandroid.R
import com.lyl.wanandroid.listener.OnItemClickListener
import com.lyl.wanandroid.listener.OnItemClickListenerTwo

/**
 * Create By: lyl
 * Date: 2019-07-26 11:42
 */
class KindContentAdapter(var context: Context, var list: List<KindContentBean>) : RecyclerView.Adapter<KindContentAdapter.KindContentViewHolder>() {

    var onItemClickListenerTwo: OnItemClickListenerTwo<KindContentBean>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KindContentViewHolder {
        return KindContentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.kindcontent_list_itemview, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: KindContentViewHolder, position: Int) {
        Glide.with(context).load(list[position].envelopePic).into(holder.image)
        holder.title.text = list[position].desc
        holder.time.text = list[position].niceDate
        holder.auther.text = list[position].author
        holder.itemView.setOnClickListener {
            onItemClickListenerTwo!!.itemClickTwo(list[position], position)
        }
    }

    inner class KindContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image: ImageView
        var title: TextView
        var time: TextView
        var auther: TextView
        var collection: ImageView

        init {
            image = itemView.findViewById(R.id.image)
            title = itemView.findViewById(R.id.title)
            time = itemView.findViewById(R.id.time)
            auther = itemView.findViewById(R.id.auther)
            collection = itemView.findViewById(R.id.collection)
        }
    }
}