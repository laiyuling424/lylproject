package com.lyl.wanandroid.ui.activity.search

import android.annotation.SuppressLint
import android.content.Intent
import android.text.InputType
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.lyl.wanandroid.Constants
import com.lyl.wanandroid.R
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.listener.OnItemClickListener
import com.lyl.wanandroid.listener.OnItemClickListenerTwo
import com.lyl.wanandroid.ui.activity.WebViewDetailActivity
import com.lyl.wanandroid.ui.base.BaseActivity
import com.lyl.wanandroid.ui.base.ExecuteOnceObserver
import com.lyl.wanandroid.util.AndroidVersion
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity(), OnItemClickListener<String>, OnItemClickListenerTwo<SearchResponseBean> {

    override fun itemClickTwo(t: SearchResponseBean, position: Int) {
        val intent = Intent()

        intent.putExtra(Constants.CONTENT_Id, t.id)
        intent.putExtra(Constants.CONTENT_URL, t.link)
        intent.putExtra(Constants.CONTENT_TITLE, t.title)
        intent.putExtra(Constants.CONTENT_AUTHOR, t.author)
        intent.putExtra(Constants.CONTENT_CHAPTER, t.chapterName)
        intent.putExtra(Constants.CONTENT_CHAPTER_ID, t.chapterId)

        startActivity(WebViewDetailActivity::class.java, intent)
    }

    var viewModelSearch: ViewModelSearch? = null

    var mSearchAdapter: SearchAdapter? = null

    override val layoutId: Int
        get() = R.layout.activity_search

    override fun initView() {
        words.let { it.onItemClickListener = this }
        setupSearchView()
        setupRecycleView()
    }

    override fun loadData() {
        getSearchWords()
    }

    private fun setupSearchView() {
        searchView.queryHint = "搜索关键字以空格方式隔开"
        searchView.inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS
        searchView.imeOptions = searchView.imeOptions or EditorInfo.IME_ACTION_SEARCH or
                EditorInfo.IME_FLAG_NO_EXTRACT_UI or EditorInfo.IME_FLAG_NO_FULLSCREEN
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchFor(query)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                words.visibility = View.GONE
                return true
            }
        })

        searchBack.setOnClickListener {
            dismiss()
        }
        scrim.setOnClickListener {
            dismiss()
        }
    }

    @SuppressLint("WrongConstant")
    private fun setupRecycleView() {
        val linearLayoutManager = LinearLayoutManager(this@SearchActivity.activity)
        linearLayoutManager.orientation = LinearLayout.VERTICAL
        searchResults.layoutManager = linearLayoutManager

        mSearchAdapter = SearchAdapter()
        mSearchAdapter.let { it!!.itemClickListener = this }
        searchResults.adapter = mSearchAdapter


    }

    private fun searchFor(query: String) {
        searchResults.visibility = View.VISIBLE
        viewModelSearch = getViewMode()
        viewModelSearch!!.searchList.observe(this, Observer(mSearchAdapter!!::submitList))

    }

    private fun getViewMode(): ViewModelSearch {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ViewModelSearch() as T
            }
        })[ViewModelSearch::class.java]
    }

    private fun dismiss() {
        // clear the background else the touch ripple moves with the translation which looks bad
        searchBack.background = null
        if (AndroidVersion.hasLollipop()) {
            finishAfterTransition()
        } else {
            finish()
        }
    }

    private fun getSearchWords() {
        ApiServer.getApiServer()
                .getSearchWordsList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(
                        onExecuteOnceNext = {
                            var list: ArrayList<String>? = ArrayList()
                            for (i in 0 until it.data!!.size) {
                                list!!.add(it.data!![i].name!!)
                            }
                            words.setListText(list)
                            loading!!.visibility = View.GONE
                        },
                        onExecuteOnceError = {

                        },
                        onExecuteOnceComplete = {

                        }
                ))
    }

    override fun itemClick(t: String, position: Int) {

    }
}
