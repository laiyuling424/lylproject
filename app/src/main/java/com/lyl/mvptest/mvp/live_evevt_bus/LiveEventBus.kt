package com.lyl.mvptest.mvp.live_evevt_bus

import androidx.lifecycle.MutableLiveData

/**
 * User: lyl
 * Date: 2019-07-16 08:55
 */

class LiveEventBus {

    companion object {
        private var instance: LiveEventBus? = null
            get() {
                if (field == null) {
                    field = LiveEventBus()
                }
                return field
            }

        fun get(): LiveEventBus {
            //细心的小伙伴肯定发现了，这里不用getInstance作为为方法名，是因为在伴生对象声明时，内部已有getInstance方法，所以只能取其他名字
            return instance!!
        }
    }

    var map: Map<String, MutableLiveData<Any>>? = HashMap()


}