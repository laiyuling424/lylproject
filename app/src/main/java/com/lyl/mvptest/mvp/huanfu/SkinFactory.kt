package com.lyl.mvptest.mvp.huanfu

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.Exception
import kotlin.reflect.KClass

/**
 * User: lyl
 * Date: 2019-07-27 11:22
 */
class SkinFactory : LayoutInflater.Factory2 {

    //装载收集起来需要换肤view的容器
    var viewList: ArrayList<SkinView> = ArrayList()

    companion object {
        private var prxfixList = arrayOf(
                "android.widget.",
                "android.view.",
                "android.webkit."
        )
    }

    override fun onCreateView(parent: View?, name: String?, context: Context?, attrs: AttributeSet?): View? {

        Log.d("lyll", "data--------------$name")
        var view: View? = null
        if (name!!.contains(".")) {
            view = onCreateView(name, context, attrs)
        } else {
            for (s in prxfixList) {
                view = onCreateView(s + name, context, attrs)
                if (view != null) {
                    break
                }
            }
        }

        //收集所有需要换肤的view
        if (view != null) {
            //如果view被实例化 就去判断这个view是否满足我们换肤的要求 然后收集起来
            parseView(view, name, attrs)
        }

        return view
    }

    public fun apply() {
        for (skinView in viewList) {
            skinView.apply()
        }
    }

    private fun parseView(view: View, name: String, attrs: AttributeSet?) {
        var itemList: ArrayList<SkinItem> = ArrayList()
        for (i in 0 until attrs!!.attributeCount) {
            //属性的名字
            var attributeName = attrs.getAttributeName(i)
            //属性的资源id
            var attributeValue = attrs.getAttributeValue(i)
            //判断每条属性是否包含 background color src。。。  background  textColor
            if (attributeName.contains("background") || attributeName.contains("textColor") || attributeName.contains("src")) {
                Log.d("lyll","attributeName====$attributeName")
                //获取资源id
                var resId = attributeValue.substring(1).toInt()
                //获取到属性的类型
                var typeName = view.resources.getResourceTypeName(resId)
                //获取到属性的值的名字
                var entryName = view.resources.getResourceEntryName(resId)
                Log.d("lyll","entryName====$entryName             typeName=====$typeName")
                var skinItem = SkinItem(attributeName, resId, entryName, typeName)
                itemList.add(skinItem)
            }
        }
        if (itemList.size > 0) {
            var skinView = SkinView(view, itemList)
            viewList.add(skinView)
            skinView.apply()
        }
    }

    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View? {
        var view: View? = null

        try {
            val aClass = context!!.classLoader.loadClass(name)
            val constructor = aClass.getConstructor(*arrayOf(Context::class.java, AttributeSet::class.java))
            view = constructor.newInstance(context, attrs) as View?
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return view
    }

    class SkinItem {
        //android:background="@color/zzzzz"
        //属性的名字 background
        var name: String? = null
        //属性的id
        var resId: Int? = null
        //属性的值的名字  zzzzz
        var entryName: String? = null
        //属性值的类型
        var typeName: String? = null

        constructor(name: String?, resId: Int?, entryName: String?, typeName: String?) {
            this.name = name
            this.resId = resId
            this.entryName = entryName
            this.typeName = typeName
        }

    }

    class SkinView {
        var view: View? = null
        var list: ArrayList<SkinItem>? = null

        constructor(view: View?, list: ArrayList<SkinItem>?) {
            this.view = view
            this.list = list
        }

        public fun apply() {
            for (skinItem in list!!) {
                Log.d("lyll","apply    skinItem===="+skinItem.name)
                Log.d("lyll","apply    skinItem==========>"+skinItem.typeName)
                when (skinItem.name) {
                    "background" -> {
                        when (skinItem.typeName) {
                            "color" -> {
                                Log.d("lyll","zzzzzzzzzzzzzz")
                                view!!.setBackgroundColor(SkinManager.getSkinManager().getColor(skinItem.resId!!))
                            }
                            "drawable" -> {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    Log.d("lyll","aaaaaaaaaaaaaa")
                                    view!!.background = SkinManager.getSkinManager().getDrawable(skinItem.resId!!)

                                } else {
                                    Log.d("lyll","bbbbbbbbbb")
                                    view!!.setBackgroundDrawable(SkinManager.getSkinManager().getDrawable(skinItem.resId!!))
                                }
                            }
                            "mipmap" -> {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    view!!.background = SkinManager.getSkinManager().getDrawable(skinItem.resId!!)

                                } else {
                                    view!!.setBackgroundDrawable(SkinManager.getSkinManager().getDrawable(skinItem.resId!!))
                                }
                            }
                        }
                    }
                    "textColor" -> {
                        if (view is TextView) {
                            Log.d("lyll","cccccccccccc")
                            (view as TextView).setTextColor(SkinManager.getSkinManager().getColor(skinItem.resId!!))
                        } else if (view is Button) {
                            (view as Button).setTextColor(SkinManager.getSkinManager().getColor(skinItem.resId!!))
                        }
                    }
                    "src" -> {

                    }
                }
            }
        }
    }
}