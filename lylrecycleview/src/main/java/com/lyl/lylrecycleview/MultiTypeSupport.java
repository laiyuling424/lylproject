package com.lyl.lylrecycleview;

/**
 * Create By: lyl
 * Date: 2019-10-26 11:40
 */
public interface MultiTypeSupport<T> {
    // 根据当前位置或者条目数据返回布局
    public int getLayoutId(T item, int position);
}
