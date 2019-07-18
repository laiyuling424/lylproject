package com.lyl.mvptest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lyl.baselibrary.ARouterMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * User: lyl
 * Date: 2019/5/5 5:37 PM
 */
public class ThirdFragment extends Fragment {

    @BindView(R.id.httpapp_MainActivity)
    Button httpapp_MainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.third_fragment_layout,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @OnClick({R.id.httpapp_MainActivity})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.httpapp_MainActivity:
                ARouter.getInstance().build(ARouterMap.httpapp_mainactivity).navigation();
                break;
        }
    }
}
