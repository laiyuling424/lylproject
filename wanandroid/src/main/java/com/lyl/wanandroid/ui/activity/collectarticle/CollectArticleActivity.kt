package com.lyl.wanandroid.ui.activity.collectarticle

import android.annotation.SuppressLint
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.ViewStub
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyl.wanandroid.R
import com.lyl.wanandroid.WanAdnroidApplication
import com.lyl.wanandroid.ui.activity.LoginActivity
import com.lyl.wanandroid.ui.activity.search.SearchActivity
import com.lyl.wanandroid.ui.base.BaseActivity
import com.lyl.wanandroid.util.SharedPreferencesUtil
import kotlinx.android.synthetic.main.activity_collect_article.*
import kotlinx.android.synthetic.main.activity_collect_web.*

class CollectArticleActivity : BaseActivity() {

    private var toolbar: Toolbar? = null
    private var recycle_view: RecyclerView? = null

    private var viewModel: ViewModelCollectArticle? = null
    private var adapter: CollectArticleAdapter? = null

    override val layoutId: Int
        get() = R.layout.activity_collect_article

    override fun loadData() {
        if (SharedPreferencesUtil.getBoolean(WanAdnroidApplication.getContext(), "island", false)) {
            viewModel = getViewModel()
            viewModel!!.collectArticleList.observe(this, Observer(adapter!!::submitList))
        } else {
            login()
        }
    }

    private fun login() {
        val view = findViewById<ViewStub>(R.id.viewstub).inflate()
        view.findViewById<TextView>(R.id.loadErrorText).text = "点我去登陆"
        view.findViewById<LinearLayout>(R.id.login).setOnClickListener {
            startActivity(LoginActivity::class.java, null)
        }
    }

    private fun getViewModel(): ViewModelCollectArticle {
        return ViewModelProviders.of(this@CollectArticleActivity).get(ViewModelCollectArticle::class.java)
    }

    override fun initView() {
        setupToolbar()

        initRecycleView()
    }

    @SuppressLint("WrongConstant")
    private fun initRecycleView() {
        recycle_view = findViewById(R.id.recycle_view)
        var layoutManager = LinearLayoutManager(this@CollectArticleActivity)
        layoutManager.orientation = LinearLayout.VERTICAL
        recycle_view!!.layoutManager = layoutManager

        adapter = CollectArticleAdapter()
        recycle_view!!.adapter = adapter
    }

    private fun setupToolbar() {
        toolbar = findViewById(com.lyl.wanandroid.R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
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
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }

}
