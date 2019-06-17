package com.lyl.wanandroid.ui.test_jetpack

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * User: lyl
 * Date: 2019-06-12 17:02
 */

class TestViewModel : ViewModel() {
    var mCurrent: MutableLiveData<String>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
            }
            return field
        }
}