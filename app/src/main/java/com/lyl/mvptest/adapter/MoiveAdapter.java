package com.lyl.mvptest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lyl.mvptest.beans.HotMovieinfo;
import com.lyl.mvptest.R;

import java.util.ArrayList;
import java.util.List;

public class MoiveAdapter extends RecyclerView.Adapter<MoiveAdapter.MyViewHolder>  {
    private Context mContext;
    private List<HotMovieinfo.SubjectsBean> mList=new ArrayList<>();

    private List<MyViewHolder> mListHolder ;


    public MoiveAdapter(Context mContext, List<HotMovieinfo.SubjectsBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }



    @Override
    public MoiveAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.moive_item_layout,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MoiveAdapter.MyViewHolder holder, int position) {
        Glide.with(mContext).load(mList.get(position).getImages().getMedium()).into(holder.image);
        holder.name.setText(mList.get(position).getOriginal_title());
        holder.rating.setText(String.valueOf(mList.get(position).getRating().getAverage()));
        holder.directors.setText(mList.get(position).getDirectors().get(0).getName());
        holder.durations.setText(mList.get(position).getYear());
        holder.casts.setText(mList.get(position).getCasts().get(0).getName());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    /**
     *希望读者有良好的编码习惯，将ViewHolder类写成静态的.
     **/
    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name,rating,directors,casts,durations;
        public MyViewHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            rating=itemView.findViewById(R.id.rating);
            directors=itemView.findViewById(R.id.directors);
            casts=itemView.findViewById(R.id.casts);
            durations=itemView.findViewById(R.id.durations);
        }
    }

}