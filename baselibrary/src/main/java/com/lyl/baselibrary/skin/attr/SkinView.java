package com.lyl.baselibrary.skin.attr;

import android.view.View;

import java.util.List;

/**
 * Email 240336124@qq.com
 * Created by Darren on 2017/3/25.
 * Version 1.0
 * Description:
 */
public class SkinView {
    private View mView;

    private List<SkinAttr> mSkinAttrs;

    public SkinView(View view, List<SkinAttr> skinAttrs) {
        this.mView = view;
        this.mSkinAttrs = skinAttrs;
    }

    public void skin(){
        for (SkinAttr attr : mSkinAttrs) {
            attr.skin(mView);
        }
    }
}
