package com.lyl.wanandroid.ui.fragment.wechatpublic

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyl.wanandroid.Constants
import com.lyl.wanandroid.R
import com.lyl.wanandroid.listener.OnItemClickListener
import com.lyl.wanandroid.listener.OnItemClickListenerTwo
import com.lyl.wanandroid.ui.activity.WebViewDetailActivity
import com.lyl.wanandroid.ui.base.BaseFragment
import com.lyl.wanandroid.util.MyLog

/**
 * User: lyl
 * Date: 2019-07-08 13:47
 */
class WeChatPublicFragment : BaseFragment(), OnItemClickListener<WeChatPublicListBean>, OnItemClickListenerTwo<WeChatContentBean> {

    override fun loadData() {

    }

    override fun initView() {

    }

    override val layoutId: Int
        get() = R.layout.wechat_fragment_layout

    private var viewModelWechatContent: ViewModelWeChatContent? = null

    private var mWeChatContentAdapter: WeChatContentAdapter? = null

    private var id: Int? = null

    override fun itemClick(t: WeChatPublicListBean, position: Int) {
        MyLog.Logd("id==>" + t.id)
        id = t.id!!
        getWeChatContentData()
    }

    override fun itemClickTwo(t: WeChatContentBean, position: Int) {
        val intent = Intent()

        intent.putExtra(Constants.CONTENT_Id, t.id)
        intent.putExtra(Constants.CONTENT_URL, t.link)
        intent.putExtra(Constants.CONTENT_TITLE, t.title)
        intent.putExtra(Constants.CONTENT_AUTHOR, t.author)
        intent.putExtra(Constants.CONTENT_CHAPTER, t.chapterName)
        intent.putExtra(Constants.CONTENT_CHAPTER_ID, t.chapterId)

        startActivity(WebViewDetailActivity::class.java, intent)
    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.wechat_fragment_layout, container, false)
//        return view
//    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        id = 408

        var wechatTitle: RecyclerView = view!!.findViewById(R.id.wechat_title)
        var wechatContent: RecyclerView = view!!.findViewById(R.id.wechat_content)

        val layoutManager = LinearLayoutManager(this@WeChatPublicFragment.activity)
        layoutManager.orientation = LinearLayout.HORIZONTAL
        wechatTitle.layoutManager = layoutManager

        var mWeChatTitleAdapter = WeChatTitleAdapter()
        mWeChatTitleAdapter.let { it.itemClickListener = this }
        wechatTitle.adapter = mWeChatTitleAdapter

        var viewModelWechatTitle = ViewModelProviders.of(this).get(ViewModelWeChatTitle::class.java)
        viewModelWechatTitle.titleLists.observe(this, Observer(mWeChatTitleAdapter::submitList))


        val linearLayoutManager = LinearLayoutManager(this@WeChatPublicFragment.activity)
        linearLayoutManager.orientation = LinearLayout.VERTICAL
        wechatContent.layoutManager = linearLayoutManager

        mWeChatContentAdapter = WeChatContentAdapter()
        mWeChatContentAdapter.let { it!!.itemClickListenerTwo = this }
        wechatContent.adapter = mWeChatContentAdapter

        getWeChatContentData()

        var adapter = this@WeChatPublicFragment.childFragmentManager

    }

    private fun getWeChatContentData() {
        viewModelWechatContent = getViewModelWeChatContent(id!!)
        viewModelWechatContent!!.contentLists.observe(this, Observer(mWeChatContentAdapter!!::submitList))
    }

    private fun getViewModelWeChatContent(id: Int): ViewModelWeChatContent {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//                val mWeChatContentResposity = WeChatContentRepository(id!!)
                return ViewModelWeChatContent(id) as T
            }
        })[ViewModelWeChatContent::class.java]
    }

}