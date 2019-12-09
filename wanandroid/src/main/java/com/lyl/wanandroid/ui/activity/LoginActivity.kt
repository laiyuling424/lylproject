package com.lyl.wanandroid.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lyl.wanandroid.R
import com.lyl.wanandroid.WanAdnroidApplication
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.ui.base.ExecuteOnceObserver
import com.lyl.wanandroid.ui.bean.LoginBean
import com.lyl.wanandroid.util.LiveDataBus
import com.lyl.wanandroid.util.MyLog
import com.lyl.wanandroid.util.SharedPreferencesUtil
import com.lyl.wanandroid.util.showToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.setOnClickListener {
            ApiServer.getApiServer()
                    .login(name.text.toString(), password.text.toString())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                        MyLog.Logd("login  success")
                        if (it.errorCode != 0) {
                            SharedPreferencesUtil.putBoolean(WanAdnroidApplication.getContext(), "islanding", false)
                            showToast("账号密码不匹配")
                        } else {
                            SharedPreferencesUtil.putBoolean(WanAdnroidApplication.getContext(), "islanding", true)
                            SharedPreferencesUtil.putString(WanAdnroidApplication.getContext(), "username", name.text.toString())
                            SharedPreferencesUtil.putString(WanAdnroidApplication.getContext(), "password", password.text.toString())

                            LiveDataBus.getInstance().with("userdata", LoginBean::class.java).postValue(it.data)
                            LiveDataBus.getInstance().with("userName", String::class.java).postValue(it.data!!.username)
                            showToast("登陆成功")
                            finish()
                        }
                    }, onExecuteOnceError = {
                        MyLog.Logd("error=" + it.message)
                    }, onExecuteOnceComplete = {

                    }))
        }


        login.setOnClickListener {
            ApiServer.getApiServer()
                    .register(register_username.text.toString(), register_password.text.toString(), register_repassword.text.toString())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                        MyLog.Logd("register  success")
                    }, onExecuteOnceError = {
                        MyLog.Logd("error=" + it.message)
                    }, onExecuteOnceComplete = {

                    }))
        }
    }


}
