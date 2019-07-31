package com.lyl.wanandroid.ui.activity.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lyl.wanandroid.R
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.ui.base.ExecuteOnceObserver
import com.lyl.wanandroid.util.MyLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.main_fragment_layout.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.setOnClickListener {
            ApiServer.getApiServer()
                    .login(name.text.toString(),password.text.toString())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                        MyLog.Logd("login  success")
                    },onExecuteOnceError = {
                        MyLog.Logd("error="+it.message)
                    },onExecuteOnceComplete = {

                    }))
        }
    }


}
