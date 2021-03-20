package com.lyl.baselibrary.skin.attr;

import android.view.View;

/**
 * Email 240336124@qq.com
 * Created by Darren on 2017/3/25.
 * Version 1.0
 * Description:
 */
public class SkinAttr {
    private String mResName;
    private SkinType mSkinType;

    public SkinAttr(String resName, SkinType skinType) {
        this.mResName = resName;
        this.mSkinType = skinType;
    }

    public void skin(View view) {
        mSkinType.skin(view,mResName);
    }
}
