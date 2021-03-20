package com.lyl.baselibrary;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.LayoutInflaterCompat;
import androidx.core.view.LayoutInflaterFactory;
import androidx.core.view.ViewCompat;

import com.lyl.baselibrary.skin.SkinManager;
import com.lyl.baselibrary.skin.SkinResource;
import com.lyl.baselibrary.skin.attr.SkinAttr;
import com.lyl.baselibrary.skin.attr.SkinView;
import com.lyl.baselibrary.skin.callback.ISkinChangeListener;
import com.lyl.baselibrary.skin.support.SkinAppCompatViewInflater;
import com.lyl.baselibrary.skin.support.SkinAttrSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * author : lyl
 * e-mail : laiyuling424@gmail.com
 * date   : 3/20/21 1:35 PM
 */
public abstract class BaseSkinActivity extends Activity implements LayoutInflaterFactory, ISkinChangeListener {
    // 后面会写插件换肤 预留的东西
    private SkinAppCompatViewInflater mAppCompatViewInflater;

    private String TAG = "BaseSkinActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory(layoutInflater, this);

        /*LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory(layoutInflater, new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

            }
        });*/
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

        // 拦截到View的创建  获取View之后要去解析
        // 1. 创建View
        // If the Factory didn't handle it, let our createView() method try
        View view = createView(parent, name, context, attrs);

        // 2. 解析属性  src  textColor  background  自定义属性
        // Log.e(TAG, view + "");

        // 2.1 一个activity的布局肯定对应多个这样的 SkinView
        if(view != null) {
            List<SkinAttr> skinAttrs = SkinAttrSupport.getSkinAttrs(context, attrs);
            SkinView skinView = new SkinView(view,skinAttrs);
            // 3.统一交给SkinManager管理
            managerSkinView(skinView);

            // 4.判断一下要不要换肤
            SkinManager.getInstance().checkChangeSkin(skinView);
        }
        return view;
    }

    /**
     * 统一管理SkinView
     */
    private void managerSkinView(SkinView skinView){
        List<SkinView> skinViews = SkinManager.getInstance().getSkinViews(this);
        if(skinViews == null){
            skinViews = new ArrayList<>();
            SkinManager.getInstance().register(this, skinViews);
        }
        skinViews.add(skinView);
    }


    public View createView(View parent, final String name, @NonNull Context context,
                           @NonNull AttributeSet attrs) {
        final boolean isPre21 = Build.VERSION.SDK_INT < 21;

        if (mAppCompatViewInflater == null) {
            mAppCompatViewInflater = new SkinAppCompatViewInflater();
        }

        // We only want the View to inherit it's context if we're running pre-v21
        final boolean inheritContext = isPre21 && true
                && shouldInheritContext((ViewParent) parent);

        return mAppCompatViewInflater.createView(parent, name, context, attrs, inheritContext,
                isPre21, /* Only read android:theme pre-L (L+ handles this anyway) */
                true /* Read read app:theme as a fallback at all times for legacy reasons */
        );
    }

    private boolean shouldInheritContext(ViewParent parent) {
        if (parent == null) {
            // The initial parent is null so just return false
            return false;
        }
        while (true) {
            if (parent == null) {
                // Bingo. We've hit a view which has a null parent before being terminated from
                // the loop. This is (most probably) because it's the root view in an inflation
                // call, therefore we should inherit. This works as the inflated layout is only
                // added to the hierarchy at the end of the inflate() call.
                return true;
            } else if (parent == getWindow().getDecorView() || !(parent instanceof View)
                    || ViewCompat.isAttachedToWindow((View) parent)) {
                // We have either hit the window's decor view, a parent which isn't a View
                // (i.e. ViewRootImpl), or an attached view, so we know that the original parent
                // is currently added to the view hierarchy. This means that it has not be
                // inflated in the current inflate() call and we should not inherit the context.
                return false;
            }
            parent = parent.getParent();
        }
    }

    @Override
    public void changeSkin(SkinResource skinResource) {

    }

    @Override
    protected void onDestroy() {
        SkinManager.getInstance().unregister(this);
        super.onDestroy();
    }
}

