package com.lyl.wanandroid.ui.fragment.first.wechatpublic

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyl.wanandroid.R
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.listener.OnItemClickListener
import com.lyl.wanandroid.ui.base.BaseFragment

/**
 * User: lyl
 * Date: 2019-07-08 13:47
 */
class WeChatPublicFragment:BaseFragment(),OnItemClickListener<WeChatPublicListBean>{

    private var id: Int? = null

    override fun itemClick(t: WeChatPublicListBean, position: Int) {
        id = t.id!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.wechat_fragment_layout, container, false)
        return view
    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        id=408

        var wechatTitle:RecyclerView=view!!.findViewById(R.id.wechat_title)
        var wechatContent:RecyclerView=view!!.findViewById(R.id.wechat_content)

        val layoutManager=LinearLayoutManager(this@WeChatPublicFragment.activity)
        layoutManager.orientation=LinearLayout.HORIZONTAL
        wechatTitle.layoutManager=layoutManager
//        wechat_content.layoutManager=layoutManager

        var mWeChatTitleAdapter=WeChatTitleAdapter()
        mWeChatTitleAdapter.let { it.itemClickListener = this }
        wechatTitle.adapter=mWeChatTitleAdapter

        var viewModelWechatTitle=ViewModelProviders.of(this).get(ViewModelWeChatTitle::class.java)
        viewModelWechatTitle.titleLists.observe(this,  Observer(mWeChatTitleAdapter::submitList))



        val linearLayoutManager=LinearLayoutManager(this@WeChatPublicFragment.activity)
        linearLayoutManager.orientation=LinearLayout.VERTICAL
        wechatContent.layoutManager=linearLayoutManager

        var mWeChatContentAdapter=WeChatContentAdapter()
//        mWeChatContentAdapter.let { it.itemClickListener = this }
        wechatContent.adapter=mWeChatContentAdapter

        var viewModelWechatContent= getViewModelWeChatContent(id!!)
        viewModelWechatContent.contentLists.observe(this,  Observer(mWeChatContentAdapter::submitList))

    }

    private fun getViewModelWeChatContent(id:Int):ViewModelWeChatContent{
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                val mWeChatContentResposity = WeChatContentRepository(id!!)
                return ViewModelWeChatContent(id) as T
            }
        })[ViewModelWeChatContent::class.java]
    }

}