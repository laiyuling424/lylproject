package com.lyl.httpapp

import java.util.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.RejectedExecutionHandler
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * User: lyl
 * Date: 2019-07-18 09:40
 */
class ThreadPoolManager {

    companion object {
        private var threadPoolManager = ThreadPoolManager()

        public fun getInstance(): ThreadPoolManager {
            return threadPoolManager
        }
    }

    public var mQueue: LinkedBlockingQueue<Runnable>? = null

    public var mThreadPoolExecutor: ThreadPoolExecutor? = null

    init {
        mQueue = LinkedBlockingQueue()
        mThreadPoolExecutor = ThreadPoolExecutor(3, 10, 15, TimeUnit.SECONDS, mQueue,
                RejectedExecutionHandler { r, executor ->
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

        mThreadPoolExecutor!!.execute(coreThread)
    }

    public fun addTask(runnable: Runnable) {
        if (runnable != null) {
            mQueue!!.add(runnable)
        }
    }


}