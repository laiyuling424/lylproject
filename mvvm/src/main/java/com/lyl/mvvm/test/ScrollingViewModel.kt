package com.lyl.mvvm.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lyl.mvvm.core.BaseViewModel
import kotlinx.coroutines.*

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


//    fun a(){
//        viewModelScope.launch {
//            withContext(Dispatchers.Main){
//
//            }
//        }
//
//        GlobalScope.launch {
//            withContext(Dispatchers.Main){
//
//            }
//        }
//
//        MainScope().launch {
//            withContext(Dispatchers.Main){
//
//            }
//        }
//    }
//
//
//    private val viewModelJob = SupervisorJob()
//    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
//    //运行在UI线程的协程
//    fun launchUIX( block: suspend CoroutineScope.() -> Unit) {
//        try {
//            uiScope.launch(Dispatchers.Main) {
//                block()
//            }
//        }catch (e:Exception){
//            e.printStackTrace()
//        }
//    }
}