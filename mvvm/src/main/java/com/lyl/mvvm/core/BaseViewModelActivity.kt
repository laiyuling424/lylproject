package com.lyl.mvvm.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.lyl.mvvm.test.BaseRepository
import kotlinx.coroutines.TimeoutCancellationException

abstract class BaseViewModelActivity<VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        initVM()
        super.onCreate(savedInstanceState)
        startObserve()
    }

    private fun initVM() {

        providerVMClass()?.let {
            viewModel = ViewModelProviders.of(this).get(it)
//            lifecycle.addObserver(viewModel)
        }
    }

    open fun providerVMClass(): Class<VM>? = null

    private fun startObserve() {
        //处理一些通用异常，比如网络超时等
        viewModel.run {
            getError().observe(this@BaseViewModelActivity, Observer {
                requestError(it)
            })
            getFinally().observe(this@BaseViewModelActivity, Observer {
                requestFinally(it)
            })
        }
    }

    open fun requestFinally(it: Int?) {

    }

    open fun requestError(it: Exception?) {
        //处理一些已知异常
        it?.run {
            when (it) {
                is TimeoutCancellationException -> {
                    println("请求超时")
                }
                is BaseRepository.TokenInvalidException -> {
                    println("登陆超时")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        lifecycle.removeObserver(viewModel)
    }
}