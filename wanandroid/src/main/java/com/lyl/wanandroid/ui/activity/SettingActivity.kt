package com.lyl.wanandroid.ui.activity

import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import com.lyl.wanandroid.R
import com.lyl.wanandroid.WanAdnroidApplication
import com.lyl.wanandroid.http.ApiServer
import com.lyl.wanandroid.ui.base.BaseActivity
import com.lyl.wanandroid.ui.base.ExecuteOnceObserver
import com.lyl.wanandroid.util.DataCleanManager
import com.lyl.wanandroid.util.MyLog
import com.lyl.wanandroid.util.SharedPreferencesUtil
import com.lyl.wanandroid.util.showToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : BaseActivity() {


    override val layoutId: Int
        get() = R.layout.activity_setting

    override fun loadData() {

    }

    override fun initView() {
        ButterKnife.bind(this)

        if (SharedPreferencesUtil.getBoolean(WanAdnroidApplication.getContext(), "islanding", false)) {
            loginout.text = "退出当前账号"
        } else {
            loginout.text = "登陆"
        }
    }

    @OnClick(R.id.img_back, R.id.ll_clear, R.id.ll_changepassword, R.id.ll_updatauserdata, R.id.loginout)
    fun onClick(view: View) {
        when (view.id) {
            R.id.img_back -> {
                finish()
            }
            R.id.ll_clear -> {
                clear()
            }
            R.id.ll_changepassword -> {
                startActivity(ChangePasswordActivity::class.java, null)
            }
            R.id.ll_updatauserdata -> {
                startActivity(UpdataUserDataActivity::class.java, null)
            }
            R.id.loginout -> {
                //TODO 这里加一个弹窗 是否loginout
                if (SharedPreferencesUtil.getBoolean(WanAdnroidApplication.getContext(), "islanding", false)) {
                    loginout()
                } else {
                    startActivity(LoginActivity::class.java, null)
                }

            }
        }
    }

    private fun clear() {
        Thread(Runnable { DataCleanManager.clearAllCache(this@SettingActivity) }).start()
        try {
            tv_clear.text = DataCleanManager.getTotalCacheSize(this@SettingActivity)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loginout() {
        ApiServer.getApiServer()
                .logout()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(
                        onExecuteOnceNext = {
                            showToast("退出登陆成功")
                            SharedPreferencesUtil.putString(WanAdnroidApplication.getContext(), "username", "")
                            SharedPreferencesUtil.putString(WanAdnroidApplication.getContext(), "password", "")
                            SharedPreferencesUtil.putBoolean(WanAdnroidApplication.getContext(), "islanding", false)
                            loginout.text = "登陆"
                        },
                        onExecuteOnceError = {
                            MyLog.Loge("error", "loginout error=${it.message}")
                            showToast("退出登陆失败")
                        },
                        onExecuteOnceComplete = {

                        }
                ))
    }
}
