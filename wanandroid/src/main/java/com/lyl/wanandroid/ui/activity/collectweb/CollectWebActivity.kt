package com.lyl.wanandroid.ui.activity.collectweb

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewStub
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import butterknife.OnClick
import com.lyl.wanandroid.R
import com.lyl.wanandroid.WanAdnroidApplication
import com.lyl.wanandroid.ui.activity.LoginActivity
import com.lyl.wanandroid.ui.activity.collectarticle.CollectArticleAdapter
import com.lyl.wanandroid.ui.activity.collectarticle.ViewModelCollectArticle
import com.lyl.wanandroid.ui.activity.search.SearchActivity
import com.lyl.wanandroid.ui.base.BaseActivity
import com.lyl.wanandroid.util.SharedPreferencesUtil
import kotlinx.android.synthetic.main.activity_collect_article.*
import kotlinx.android.synthetic.main.activity_collect_web.*

class CollectWebActivity : BaseActivity() {

    private var toolbar: Toolbar? = null
    private var recycle_view: RecyclerView? = null

    private var viewModel: ViewModelCollectWeb? = null
    private var adapter: CollectWebAdapter? = null

    override val layoutId: Int
        get() = R.layout.activity_collect_web

    override fun loadData() {
        if (SharedPreferencesUtil.getBoolean(WanAdnroidApplication.getContext(), "island", false)) {
            viewModel = getViewModel()
            viewModel!!.collectWebList.observe(this, Observer(adapter!!::submitList))
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

    private fun getViewModel(): ViewModelCollectWeb {
        return ViewModelProviders.of(this@CollectWebActivity).get(ViewModelCollectWeb::class.java)
    }

    override fun initView() {
        ButterKnife.bind(this@CollectWebActivity)

        setupToolbar()

        initRecycleView()
    }

    @SuppressLint("WrongConstant")
    private fun initRecycleView() {
        recycle_view = findViewById(R.id.recycle_view)
        var layoutManager = LinearLayoutManager(this@CollectWebActivity)
        layoutManager.orientation = LinearLayout.VERTICAL
        recycle_view!!.layoutManager = layoutManager

        adapter = CollectWebAdapter()
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
