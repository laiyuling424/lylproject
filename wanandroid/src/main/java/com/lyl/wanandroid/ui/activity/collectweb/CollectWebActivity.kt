package com.lyl.wanandroid.ui.activity.collectweb

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lyl.wanandroid.R
import com.lyl.wanandroid.ui.activity.collectarticle.CollectArticleAdapter
import com.lyl.wanandroid.ui.activity.collectarticle.ViewModelCollectArticle
import com.lyl.wanandroid.ui.activity.search.SearchActivity
import com.lyl.wanandroid.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_collect_article.*

class CollectWebActivity : BaseActivity() {

    private var toolbar: Toolbar? = null

    private var viewModel: ViewModelCollectWeb?=null
    private var adapter:CollectWebAdapter?=null

    override val layoutId: Int
    get() = R.layout.activity_collect_web

    override fun loadData() {
        viewModel=getViewModel()
        viewModel!!.collectWebList.observe(this, Observer (adapter!!::submitList))
    }

    private fun getViewModel() :ViewModelCollectWeb{
        return ViewModelProviders.of(this@CollectWebActivity).get(ViewModelCollectWeb::class.java)
    }

    override fun initView() {
        setupToolbar()

        initRecycleView()
    }

    @SuppressLint("WrongConstant")
    private fun initRecycleView() {
        var layoutManager= LinearLayoutManager(this@CollectWebActivity)
        layoutManager.orientation= LinearLayout.VERTICAL
        recycle_view.layoutManager=layoutManager

        adapter= CollectWebAdapter()
        recycle_view.adapter=adapter
    }

    private fun setupToolbar() {
        toolbar = findViewById(com.lyl.wanandroid.R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
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

}
