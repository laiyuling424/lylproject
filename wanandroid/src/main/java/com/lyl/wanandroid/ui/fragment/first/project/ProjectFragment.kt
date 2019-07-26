package com.lyl.wanandroid.ui.fragment.first.project

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.gson.Gson
import com.lyl.wanandroid.Constants
import com.lyl.wanandroid.R
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.listener.OnItemClickListener
import com.lyl.wanandroid.listener.OnItemClickListenerTwo
import com.lyl.wanandroid.ui.activity.WebViewDetailActivity

import com.lyl.wanandroid.ui.base.BaseFragment
import com.lyl.wanandroid.ui.base.ExecuteOnceObserver
import com.lyl.wanandroid.util.MyLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.project_fragment_layout.*

/**
 * User: lyl
 * Date: 2019-07-19 14:10
 */
class ProjectFragment : BaseFragment(), OnItemClickListener<KindBean>, OnItemClickListenerTwo<KindContentBean> {

    override fun itemClick(t: KindBean, position: Int) {
        MyLog.Logd("here!!!!!")
        adapter!!.setSelect(t)
        loadKindContext(t.id)
        drawerlayout.closeDrawer(GravityCompat.END)
    }

    override fun itemClickTwo(t: KindContentBean, position: Int) {
        MyLog.Logd("zzhere!!!!!")
        val intent = Intent()

        intent.putExtra(Constants.CONTENT_Id, t.id)
        intent.putExtra(Constants.CONTENT_URL, t.link)
        intent.putExtra(Constants.CONTENT_AUTHOR, t.author)
        intent.putExtra(Constants.CONTENT_CHAPTER, t.chapterName)
        intent.putExtra(Constants.CONTENT_CHAPTER_ID, t.chapterId)
        intent.putExtra(Constants.CONTENT_TITLE, t.title)

        startActivity(WebViewDetailActivity::class.java, intent)
    }

    var viewModel:ViewModelKind?=null

    var adapter:KindAdapter?=null

    private var kindContentAdapter:KindContentAdapter?=null

    var list:ArrayList<KindContentBean>?=null

    override val layoutId: Int
        get() = R.layout.project_fragment_layout

    override fun loadData() {

        viewModel=ViewModelProviders.of(this@ProjectFragment)[ViewModelKind::class.java]

        viewModel!!.kindList.observe(this, Observer ( adapter!!::submitList ))

        loadKindContext(294)
    }

    override fun initView() {
        ButterKnife.bind(this, this!!.mView!!)

        initRecycle()

        tvKind.setOnClickListener{
            drawerlayout.openDrawer(GravityCompat.END)
        }
    }

    @SuppressLint("WrongConstant")
    private fun initRecycle() {
        var linearLayoutManager=LinearLayoutManager(context)
        linearLayoutManager.orientation=LinearLayout.VERTICAL
        content_recycle_view.layoutManager=linearLayoutManager

        linearLayoutManager=LinearLayoutManager(context)
        linearLayoutManager.orientation=LinearLayout.VERTICAL
        kind_recycle_view.layoutManager=linearLayoutManager

        adapter= KindAdapter()
        adapter!!.setSelect(KindBean())
        adapter!!.itemClickListener=this@ProjectFragment
        kind_recycle_view.adapter=adapter

        list=ArrayList()
        kindContentAdapter= KindContentAdapter(context!!, list!!)
        kindContentAdapter!!.onItemClickListenerTwo=this@ProjectFragment
        content_recycle_view.adapter=kindContentAdapter
    }

//    @OnClick(R.id.tvKind)
//    public fun onClick(view:View){
//        when(view.id){
//            R.id.tvKind->{
//                MyLog.Logd("aaaaaaaaaa")
//                drawerlayout.openDrawer(GravityCompat.END)
//            }
//        }
//    }

    private fun loadKindContext(id: Int?) {
        MyLog.Logd("errzzzzzzzzzzzzor==")
        ApiServer.getApiServer().getkindContentList(1, id!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(
                        onExecuteOnceNext = {
                            MyLog.Logd("data===>"+Gson().toJson(it))
                            list!!.clear()
                            list!!.addAll(it.data!!.datas!!)
                            kindContentAdapter!!.notifyDataSetChanged()
                        },
                        onExecuteOnceError = {
                            MyLog.Logd("error=="+it.message)
                        },
                        onExecuteOnceComplete = {

                        }
                ))
    }
}