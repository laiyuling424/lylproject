package com.lyl.httpapp

import android.util.Log
import java.util.concurrent.*

/**
 * User: lyl
 * Date: 2019-07-18 09:40
 */
class ThreadPoolManager {

    companion object {
        private var threadPoolManager = ThreadPoolManager()

        public fun getInstance(): ThreadPoolManager{
            return threadPoolManager
        }
    }

    private var mQueue: LinkedBlockingQueue<Runnable>? = null

    private var mDelayQueue = DelayQueue<HttpTask<*>>()

    public var mThreadPoolExecutor: ThreadPoolExecutor? = null

    init {
        mQueue = LinkedBlockingQueue()
        mThreadPoolExecutor = ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS, mQueue,
                RejectedExecutionHandler { r, _ ->
                    //将拒绝的线程重新放入
                    addTask(r!!)
                })
        //创建叫号员线程，不断从队列获取
        var coreThread: Runnable = object : Runnable {
            var runn: Runnable? = null
            override fun run() {
                while (true) {
                    runn = mQueue!!.take()
                    mThreadPoolExecutor!!.execute(runn)
                }
            }
        }

        var delayThread = object : Runnable {
            var ht: HttpTask<*>? = null
            override fun run() {
                while (true) {
                    ht = mDelayQueue.take()

                    if (ht!!.getRetryCount()!! < 3) {
                        mThreadPoolExecutor!!.execute(ht)
                        ht!!.setRetryCount(ht!!.getRetryCount()!! + 1)
                        Log.d("lyll", "========重试机制=======>" + ht!!.getRetryCount()!! + "       " + System.currentTimeMillis())
                    } else {
                        Log.d("lyll", "重试超过3次 不再执行")
                    }

                }
            }
        }

        mThreadPoolExecutor!!.execute(coreThread)
        mThreadPoolExecutor!!.execute(delayThread)
    }

    public fun addTask(runnable: Runnable) {
//        if (runnable != null) {
            mQueue!!.add(runnable)
//        }
    }

    public fun addDelayTask(ht: HttpTask<*>) {
//        if (ht != null) {
            ht.setDelayTime(3000)
            mDelayQueue.offer(ht)
//        }
    }


}