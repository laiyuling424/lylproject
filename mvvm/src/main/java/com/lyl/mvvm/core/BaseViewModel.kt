package com.lyl.mvvm.core

import androidx.lifecycle.*
import kotlinx.coroutines.*
import java.lang.Exception

/**
 * author : lyl
 * e-mail : laiyuling424@gmail.com
 * date   : 5/14/21 11:23 AM
 */
open class BaseViewModel : ViewModel(){

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    private val error by lazy { MutableLiveData<Exception>() }

    private val finally by lazy { MutableLiveData<Int>() }

    //运行在UI线程的协程
//    fun launchUI(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch {
//        try {
//            withTimeout(5000){
//                block()
//            }
//        } catch (e: Exception) {
//            error.value = e
//        } finally {
//            finally.value = 200
//        }
//    }

    //运行在UI线程的协程
    fun launchUI( block: suspend CoroutineScope.() -> Unit) {
        try {
            uiScope.launch(Dispatchers.Main) {
                block()
            }
        } catch (e: Exception) {
            error.value = e
        } finally {
            finally.value = 200
        }
    }

    /**
     * 请求失败，出现异常
     */
    fun getError(): LiveData<Exception> {
        return error
    }

    /**
     * 请求完成，在此处做一些关闭操作
     */
    fun getFinally(): LiveData<Int> {
        return finally
    }

}