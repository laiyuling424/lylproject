package com.lyl.baselibrary.skin.support;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.lyl.baselibrary.skin.attr.SkinAttr;
import com.lyl.baselibrary.skin.attr.SkinType;

import java.util.ArrayList;
import java.util.List;

/**
 * Email 240336124@qq.com
 * Created by Darren on 2017/3/25.
 * Version 1.0
 * Description: 皮肤属性解析的支持类
 */
public class SkinAttrSupport {

    private static final String TAG = "SkinAttrSupport";

    /**
     * 获取SkinAttr的属性
     *
     * @param context
     * @param attrs
     * @return
     */
    public static List<SkinAttr> getSkinAttrs(Context context, AttributeSet attrs) {
        // background   src  textColor
        List<SkinAttr> skinAttrs = new ArrayList<>();

        int attrLength = attrs.getAttributeCount();
        for (int index = 0; index < attrLength; index++) {
            // 获取名称 , 值
            String attrName = attrs.getAttributeName(index);
            String attrValue = attrs.getAttributeValue(index);
            // Log.e(TAG,"attrName -> "+attrName +" ; attrValue -> "+attrValue);
            // 只获取重要的
            SkinType skinType = getSkinType(attrName);
            if (skinType != null) {
                // 资源名称  目前只有attrValue 是一个 @int类型
                String resName = getResName(context, attrValue);
                if (TextUtils.isEmpty(resName)) {
                    continue;
                }
                SkinAttr skinAttr = new SkinAttr(resName, skinType);
                skinAttrs.add(skinAttr);
            }
        }
        return skinAttrs;
    }

    /**
     * 获取资源的名称
     *
     * @param context
     * @param attrValue
     * @return
     */
    private static String getResName(Context context, String attrValue) {

        if (attrValue.startsWith("@")) {
            attrValue = attrValue.substring(1);

            int resId = Integer.parseInt(attrValue);

            return context.getResources().getResourceEntryName(resId);
        }

        return null;
    }

    /**
     * 通过名称获取SkinType
     *
     * @param attrName
     * @return
     */
    private static SkinType getSkinType(String attrName) {
        SkinType[] skinTypes = SkinType.values();
        for (SkinType skinType : skinTypes) {
            if (skinType.getResName().equals(attrName)) {
                return skinType;
            }
        }
        return null;
    }
}
