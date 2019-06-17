package com.lyl.mvptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lyl.mvptest.mvp.animation.AnimationActivity;
import com.lyl.mvptest.mvp.cv.CvActivity;
import com.lyl.mvptest.mvp.radar_view.RadarViewActivity;
import com.lyl.mvptest.mvp.suefaceview.DrawActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * User: lyl
 * Date: 2019/5/5 5:28 PM
 */
public class SecondFragment extends Fragment {
    private Unbinder unbinder;

    @BindView(R.id.btn1)
    Button btn1;

    @BindView(R.id.btn2)
    Button btn2;

    @BindView(R.id.btn3)
    Button btn3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment_layout, container, false);

        unbinder = ButterKnife.bind(this, view);

//        btn1=(Button)view.findViewById(R.id.btn1);
//
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getContext(), AnimationActivity.class));
//            }
//        });
//
//        btn2=(Button)view.findViewById(R.id.btn2);
//
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getContext(), CvActivity.class));
//            }
//        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnClick({R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn1:
                startActivity(new Intent(getContext(), AnimationActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(getContext(), CvActivity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(getContext(), DrawActivity.class));
                break;
            case R.id.btn4:
                startActivity(new Intent(getContext(), RadarViewActivity.class));
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
