package com.lyl.secondtheory.eventbus

import android.util.Log


/**
 * Create By: lyl
 * Date: 2019-07-17 09:33
 */
class EventBus private constructor() {

    private var map: HashMap<Any, List<SubscribleMothod>>? = null

    init {
        map = HashMap()
    }

    companion object {
        val getDefault: EventBus by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            EventBus()
        }
    }

    public fun registered(any: Any) {
        var list: List<SubscribleMothod>? = ArrayList()

        if (list!!.isEmpty()) {
            list = findSubscribleMothods(any)
            map!![any] = list!!
        }
    }

    private fun findSubscribleMothods(any: Any): List<SubscribleMothod>? {
        var list: ArrayList<SubscribleMothod>? = ArrayList()
        var clazz = any.javaClass

        while (clazz != null) {
            var name = clazz.name
//            MyLog.Logd("lyll", "calss name===>$name")
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.")) {
                break
            }

            var methods = clazz.declaredMethods
            for (method in methods) {
//                MyLog.Logd("lyll", "method name===>${method.name}")
                var subscrible: Subscrible? = method.getAnnotation(Subscrible::class.java) ?: continue

                var types = method.parameterTypes
                if (types.size != 1) {
                    Log.d("lyll", "eventbus only accpet one para")
                }

                var threadMode = subscrible!!.threadMode

                var sbm = SubscribleMothod(method, types[0], threadMode)

                list!!.add(sbm)
            }
            clazz = (clazz.superclass as Class<Any>?)!!
        }
        return list
    }

    public fun post(type: Any) {
        map!!.entries.forEach {
            var list = it.value
            Log.d("lyll", "map key===>" + it.key.toString())
            Log.d("lyll", "map value===>" + it.value[0].name.toString() + "     " + it.value[0].threadMode.toString() + "     " + it.value[0].type.toString())
            for (subscribleMothod in list) {
                Log.d("lyll", "subscribleMothod.type!!.javaClass===>" + subscribleMothod.type!!.javaClass)
                Log.d("lyll", "type.javaClass===>" + type.javaClass)

                //todo 不知道为什么二个数据类不一样
//                if (subscribleMothod.type!!.javaClass.isAssignableFrom(type.javaClass)) {
                if (subscribleMothod.type.toString().equals(type.javaClass.toString())) {
                    invoke(subscribleMothod, it.key, type)
                }
            }
        }
    }

    private fun invoke(subscribleMothod: SubscribleMothod, key: Any, type: Any) {
        var method = subscribleMothod.name
        method!!.invoke(key, type)
    }
}