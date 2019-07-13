package com.lyl.wanandroid.ui.activity.tixi_detail

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyl.wanandroid.Constants
import com.lyl.wanandroid.R
import com.lyl.wanandroid.listener.OnItemClickListener
import com.lyl.wanandroid.ui.activity.WebViewDetailActivity
import com.lyl.wanandroid.ui.activity.search.SearchActivity
import com.lyl.wanandroid.ui.base.BaseActivity
import com.lyl.wanandroid.ui.fragment.first.main.MainArticleAdapter
import com.lyl.wanandroid.ui.fragment.first.main.MainArticleBean

class TixiDetailActivity : BaseActivity(),OnItemClickListener<MainArticleBean> {

    var toolbar: Toolbar? = null

    var cid: Int = -1

    var title: String? = null

    var viewModelTixiDetail: ViewModelTixiDetail? = null

    var adapter: MainArticleAdapter? = null

    override val layoutId: Int
        get() = R.layout.activity_tixi_detail

    override fun loadData() {
        viewModelTixiDetail = getViewModel(cid)

        viewModelTixiDetail!!.tixiDetailList.observe(this, Observer(adapter!!::submitList))
    }

    override fun initView() {
        initData()
        setupToolbar()
        initRecycleView()
    }

    @SuppressLint("WrongConstant")
    private fun initRecycleView() {
        var recyclerView: RecyclerView = findViewById(R.id.recycle_view)

        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayout.VERTICAL

        recyclerView.layoutManager = linearLayoutManager

        adapter = MainArticleAdapter()

        recyclerView.adapter = adapter

        adapter!!.let { it.itemClickListener=this }
    }

    private fun initData() {
        cid = intent.getIntExtra(Constants.CONTENT_Id, -1)
        title = intent.getStringExtra(Constants.CONTENT_TITLE)
    }

    protected fun setupToolbar() {
        toolbar = findViewById(com.lyl.wanandroid.R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar!!.title = title
        toolbar!!.setTitleTextColor(Color.WHITE)

        //点击左边返回按钮监听事件
        toolbar!!.setNavigationOnClickListener(View.OnClickListener {
            finish()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    @SuppressLint("RestrictedApi")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_search -> {
                startActivity(Intent(this, SearchActivity::class.java))
            }
        }
        return true
    }

    private fun getViewModel(cid: Int): ViewModelTixiDetail {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ViewModelTixiDetail(cid) as T
            }
        }).get(ViewModelTixiDetail::class.java)
    }

    override fun itemClick(t: MainArticleBean, position: Int) {
        val intent = Intent()

        intent.putExtra(Constants.CONTENT_Id, t.id)
        intent.putExtra(Constants.CONTENT_URL, t.link)
        intent.putExtra(Constants.CONTENT_AUTHOR, t.author)
        intent.putExtra(Constants.CONTENT_CHAPTER, t.chapterName)
        intent.putExtra(Constants.CONTENT_CHAPTER_ID, t.chapterId)
        intent.putExtra(Constants.CONTENT_TITLE, t.title)

        startActivity(WebViewDetailActivity::class.java, intent)
    }
}
