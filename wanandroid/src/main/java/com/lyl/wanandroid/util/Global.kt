package com.lyl.wanandroid.util

import android.annotation.SuppressLint
import android.app.Application
import android.os.Looper
import android.widget.Toast
import com.lyl.wanandroid.WanAdnroidApplication

/**
 * User: lyl
 * Date: 2019-06-11 14:50
 */

private var toast: Toast? = null

/**
 * 弹出Toast信息。如果不是在主线程中调用此方法，Toast信息将会不显示。
 *
 * @param content
 * Toast中显示的内容
 */
@SuppressLint("ShowToast")
@JvmOverloads
fun showToast(content: String) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        if (toast == null) {
            toast = Toast.makeText(WanAdnroidApplication.context, content,Toast.LENGTH_SHORT)
        } else {
            toast?.setText(content)
        }
        toast?.show()
    }
}

/**
 * 切换到主线程后弹出Toast信息。此方法不管是在子线程还是主线程中，都可以成功弹出Toast信息。
 *
 * @param content
 * Toast中显示的内容
 * @param duration
 * Toast显示的时长
 */
//@SuppressLint("ShowToast")
//@JvmOverloads
//fun showToastOnUiThread(content: String, duration: Int = Toast.LENGTH_SHORT) {
//    GifFun.getHandler().post {
//        if (toast == null) {
//            toast = Toast.makeText(GifFun.getContext(), content, duration)
//        } else {
//            toast?.setText(content)
//        }
//        toast?.show()
//    }
//}