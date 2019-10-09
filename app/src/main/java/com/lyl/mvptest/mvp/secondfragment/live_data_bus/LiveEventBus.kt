package com.lyl.mvptest.mvp.secondfragment.live_data_bus

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.Map

/**
 * Create By: lyl
 * Date: 2019-07-16 08:55
 */

class LiveEventBus {
    private val liveEventBus = LiveEventBus()

    private var bus: MutableMap<String, BusMutableLiveData<Any>>? = null

    fun getInstance() {
        return liveEventBus
    }

    private fun LiveEventBus() {
        bus = HashMap<String, BusMutableLiveData<Any>>()
    }

    @Synchronized
    fun <T> with(key: String, type: Class<T>): BusMutableLiveData<T> {
        if (!bus!!.containsKey(key)) {
            bus!!.put(key, BusMutableLiveData<Any>())
        }
        return (bus!!.get(key) as BusMutableLiveData<T>?)!!
    }

    class BusMutableLiveData<T> : MutableLiveData<T>() {
        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            super.observe(owner, observer)
            try {
                hook(observer)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @Throws(Exception::class)
        private fun hook(observer: Observer<*>) {
            val liveDataClass = LiveData::class.java
            val mObservers = liveDataClass!!.getDeclaredField("mObservers")
            mObservers.setAccessible(true)

            val mObserversClass = mObservers.get(this).javaClass

            val mapget = mObserversClass.getDeclaredMethod("get", Any::class.java!!)
            mapget.setAccessible(true)

            val invokeEntry = mapget.invoke(mObservers.get(this), observer)

            var mapvalue: Any? = null
            if (invokeEntry != null && invokeEntry is Map.Entry<*, *>) {
                mapvalue = (invokeEntry as Map.Entry<*, *>).value
            }

            if (mapvalue == null) {
                throw NullPointerException("null")
            }

            val calss = mapvalue!!.javaClass.getSuperclass()

            val mLastVersion = calss!!.getDeclaredField("mLastVersion")
            mLastVersion.setAccessible(true)

            val mVersion = liveDataClass!!.getDeclaredField("mVersion")
            mVersion.setAccessible(true)
            val o = mVersion.get(this)

            mLastVersion.set(mapvalue, o)
        }

    }


}