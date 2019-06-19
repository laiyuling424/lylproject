package com.lyl.mvptest.mvp.recycleview;


import androidx.recyclerview.widget.RecyclerView;

public interface ItemTouchStatus {
    boolean onItemRemove(int position);

    void onSaveItemStatus(RecyclerView.ViewHolder viewHolder);
}