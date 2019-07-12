package com.lyl.wanandroid.ui.test_jetpack

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.*
import androidx.lifecycle.Transformations.map
import com.lyl.wanandroid.R
import com.lyl.wanandroid.ui.base.BaseActivity
import com.lyl.wanandroid.ui.test_jetpack.paging.ByItemActivity
import kotlinx.android.synthetic.main.activity_lifecycle.*

class LifecycleActivity : BaseActivity(), LifecycleOwner {
    override val layoutId: Int
        get() = R.layout.activity_lifecycle

    override fun loadData() {

    }

    override fun initView() {

    }

    lateinit var lifecycle: LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_lifecycle)


        //lifecycle
        lifecycle = LifecycleRegistry(this)
        var myObserver = MyObserver(lifecycle, object : CallBack {
            override fun update(msg: String) {
                Toast.makeText(this@LifecycleActivity, msg, Toast.LENGTH_SHORT).show()
            }

        })
        lifecycle.addObserver(myObserver)
        lifecycle.markState(Lifecycle.State.CREATED)


        //viewmodel
        var textView: TextView = findViewById(R.id.tv1)
        val model = ViewModelProviders.of(this)[MyModel::class.java]
        textView.text = model.textName

        findViewById<Button>(R.id.btn_change).setOnClickListener {
            model.textName = "Change = 22222"
            textView.text = model.textName
        }


        //LiveData
        //创建观察者对象
        val nameObservable = Observer<String> {
            // 创建观察者对象
            textView.text = it       // 定义onChange（）方法中的操作
        }
        // 如果在View Model中使用，先创建ViewModel的对象
        val mModel = ViewModelProviders.of(this).get(TestViewModel::class.java)
        // mCurrent 订阅观察
        mModel.mCurrent!!.observe(this, nameObservable)
        // 设置两个点击事件，修改LiveData中的数据
        btnA.setOnClickListener { mModel.mCurrent!!.value = "AAAAA" }
        btnB.setOnClickListener { mModel.mCurrent!!.value = "BBBBB" }

        // 将返回开始示例中的 mCurrent 值的长度
//        var liveDataMap: LiveData<Int> = Transformations.map<String, Int>(mModel.mCurrent!!) { input: String -> input.length }
        var liveDataMap: LiveData<Int> = map<String, Int>(mModel.mCurrent!!){ String -> String.length }


        findViewById<Button>(R.id.btnC).setOnClickListener {
            startActivity(Intent(this,ByItemActivity::class.java))
        }
    }


    override fun getLifecycle(): Lifecycle {
        return lifecycle
    }

//    override fun onStart() {
//        super.onStart()
//        lifecycle.markState(Lifecycle.State.STARTED)
//    }
//
//
//
//    override fun onResume() {
//        super.onResume()
//        lifecycle.markState(Lifecycle.State.RESUMED)
//    }
//
//
//    override fun onDestroy() {
//        super.onDestroy()
//
//        lifecycle.markState(Lifecycle.State.DESTROYED)
//    }
}
