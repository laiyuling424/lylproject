package com.lyl.wanandroid.ui.fragment.first.tixi

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lyl.wanandroid.R
import com.lyl.wanandroid.ui.base.BaseFragment
import android.graphics.drawable.Drawable
import com.lyl.wanandroid.WanAdnroidApplication.Companion.context


/**
 * User: lyl
 * Date: 2019-07-13 09:05
 */
class TixiFragment : BaseFragment() {

    var adapter: TixiAdapter? = null

    var recyclerView: RecyclerView? = null

    override val layoutId: Int
        get() = R.layout.tixi_fragment_layout

    override fun loadData() {
        var viewModelTixi = getViewModel()
        viewModelTixi.tixiList.observe(this, Observer(adapter!!::submitList))
    }

    @SuppressLint("WrongConstant")
    override fun initView() {
        recyclerView = mView!!.findViewById(com.lyl.wanandroid.R.id.recycle_view)

        var linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayout.VERTICAL

        recyclerView!!.layoutManager = linearLayoutManager

        adapter = TixiAdapter(this.context!!)
        recyclerView!!.adapter = adapter

        var tixiItemDecoration = TixiItemDecoration(this.context!!)

        recyclerView!!.addItemDecoration(tixiItemDecoration)
    }

    private fun getViewModel(): ViewModelTixi {
        return ViewModelProviders.of(this@TixiFragment)[ViewModelTixi::class.java]
    }

    class TixiItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

        private var height: Int = 100
        private var mDividerDrawable: Drawable? = null

        init {
            mDividerDrawable = context.resources.getDrawable(com.lyl.wanandroid.R.drawable.recycle_view_item_decoration)
        }


        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            val left = parent.paddingLeft
            val right = parent.measuredWidth - parent.paddingRight
            val count = parent.childCount
            for (i in 0 until count) {
                val child = parent.getChildAt(i)
                val layoutParams = child.layoutParams as RecyclerView.LayoutParams
                val top = child.bottom + layoutParams.bottomMargin
                val bottom = top + height
                mDividerDrawable!!.setBounds(left, top, right, bottom)
                mDividerDrawable!!.draw(c)
            }
//            super.onDraw(c, parent, state)
        }


        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            var layoutPosition = parent.getChildViewHolder(view).layoutPosition
            if (layoutPosition == parent.adapter!!.itemCount - 1) {
                outRect.set(0, 0, 0, 0)
            } else {
                outRect.set(0, 0, 0, height)
            }
//            super.getItemOffsets(outRect, view, parent, state)
        }
    }
}