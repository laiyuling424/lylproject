package com.lyl.wanandroid.ui.mainactivity

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.lyl.wanandroid.R
import com.lyl.wanandroid.ui.base.BaseActivity
import com.lyl.wanandroid.ui.fragment.FirstFragment
import com.lyl.wanandroid.ui.fragment.Thirdfragment
import com.lyl.wanandroid.ui.fragment.wechatpublic.WeChatPublicFragment
import com.lyl.wanandroid.util.showToast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : BaseActivity() {


    private lateinit var pagerAdapter: Adapter

    private var backPressTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

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

        var adapter=Adapter(this@MainActivity.supportFragmentManager)

        adapter.addFragment(FirstFragment(),"FirstFragment")
//        adapter.addFragment(SecondFragment(),"SecondFragment")
        adapter.addFragment(WeChatPublicFragment(),"微信公众号")
        adapter.addFragment(Thirdfragment(),"Thirdfragment")

        viewpager!!.adapter=adapter

        viewpager.currentItem=0
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewpager.currentItem=0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                viewpager.currentItem=1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                viewpager.currentItem=2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }



    override fun onBackPressed() {

        val now = System.currentTimeMillis()
        if (now - backPressTime > 2000) {
            showToast("再按一次退出")
            backPressTime = now
        } else {
            super.onBackPressed()
        }

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
