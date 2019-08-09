package com.lyl.mvptest.mvp.secondfragment.recycleview;


import androidx.recyclerview.widget.RecyclerView;

public interface ItemTouchStatus {
    boolean onItemRemove(int position);

    void onSaveItemStatus(RecyclerView.ViewHolder viewHolder);
}