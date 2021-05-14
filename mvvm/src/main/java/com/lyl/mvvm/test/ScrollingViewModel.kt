package com.lyl.mvvm.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lyl.mvvm.core.BaseViewModel

class ScrollingViewModel : BaseViewModel() {

    private val TAG = ScrollingViewModel::class.java.simpleName

    private val datas: MutableLiveData<List<Data>> by lazy { MutableLiveData<List<Data>>().also { loadDatas() } }

    private val repository = ArticleRepository()

    fun getActicle(): LiveData<List<Data>> {
        return datas
    }

    fun loadDatas() = launchUI {
            val result = repository.getDatas()
            datas.value = result.data
    }
}