package com.lyl.mvptest.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lyl.mvptest.R;
import com.lyl.mvptest.beans.HotMovieinfo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * create 2018/8/23
 * author lyl
 */
public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.MyViewHolder> {
    private Context context;
    private List<HotMovieinfo.SubjectsBean> list;
    private int n;

    public ActorAdapter(Context context, List<HotMovieinfo.SubjectsBean> list,int n) {
        this.context=context;
        this.list=list;
        this.n=n;
    }

    @NonNull
    @Override
    public ActorAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.casts_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorAdapter.MyViewHolder holder, int position) {
        /**
         * {"alt":"https://movie.douban.com/celebrity/1274242/",
         * "avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp",
         * "large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp",
         * "medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp"},
         * "name":"黄渤","id":"1274242"}
         */
/*        Log.d("lyll","position--"+position);
        Log.d("lyll","cast--"+list.get(n).getCasts().size());
        Log.d("lyll","cast01--"+list.get(n).getCasts().get(0).getName());
        Log.d("lyll","cast02--"+list.get(n).getCasts().get(1).getName());
        Log.d("lyll","cast03--"+list.get(n).getCasts().get(2).getName());*/

        Glide.with(context).load(list.get(n).getCasts().get(position).getAvatars().getMedium()).into(holder.image);
        holder.name.setText(list.get(n).getCasts().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.get(n).getCasts().size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        View itemDetailView;
        ImageView image;
        TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemDetailView=itemView;
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.casts);
        }
    }
}
