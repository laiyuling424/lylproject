package com.lyl.wanandroid.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.lyl.wanandroid.ui.base.BaseActivity
import com.lyl.wanandroid.ui.fragment.FirstFragment
import com.lyl.wanandroid.ui.fragment.Thirdfragment
import com.lyl.wanandroid.ui.fragment.wechatpublic.WeChatPublicFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import android.widget.Toast
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.lyl.wanandroid.R
import com.lyl.wanandroid.ui.activity.search.SearchActivity


class MainActivity : BaseActivity() {

    var toolbar: Toolbar? = null

    private lateinit var pagerAdapter: Adapter

    private var backPressTime = 0L

    override val layoutId: Int
        get() = com.lyl.wanandroid.R.layout.activity_main

    override fun loadData() {

    }

    override fun initView() {
        setupToolbar()
    }

    protected fun setupToolbar() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(com.lyl.wanandroid.R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(com.lyl.wanandroid.R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun setupViews() {
        super.setupViews()
        setupViewPager(viewpager)
    }

    private fun setupViewPager(viewpager: ViewPager?) {
//        var fragmentList:ArrayList<Fragment>?=null
//        var mWeChatPublicFragment=WeChatPublicFragment()
//        fragmentList!!.add(mWeChatPublicFragment)

        var adapter= Adapter(this@MainActivity.supportFragmentManager)

        adapter.addFragment(FirstFragment(),"FirstFragment")
//        adapter.addFragment(SecondFragment(),"SecondFragment")
        adapter.addFragment(WeChatPublicFragment(),"微信公众号")
        adapter.addFragment(Thirdfragment(),"Thirdfragment")

        viewpager!!.adapter=adapter

        viewpager.currentItem=0
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            com.lyl.wanandroid.R.id.navigation_home -> {
                viewpager.currentItem=0
                return@OnNavigationItemSelectedListener true
            }
            com.lyl.wanandroid.R.id.navigation_dashboard -> {
                viewpager.currentItem=1
                return@OnNavigationItemSelectedListener true
            }
            com.lyl.wanandroid.R.id.navigation_notifications -> {
                viewpager.currentItem=2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    private var mExitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
                mExitTime = System.currentTimeMillis()
            } else {
                System.exit(0)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    internal class Adapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val mFragments = ArrayList<Fragment>()
        private val mFragmentTitles = ArrayList<String>()

        fun addFragment(fragment: Fragment, title: String) {
            mFragments.add(fragment)
            mFragmentTitles.add(title)
        }

        override fun getItem(position: Int): Fragment {
            return mFragments[position]
        }

        override fun getCount(): Int {
            return mFragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitles[position]
        }
    }
}
