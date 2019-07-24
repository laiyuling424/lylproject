package com.lyl.mvptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lyl.mvptest.mvp.animation.AnimationActivity;
import com.lyl.mvptest.mvp.cv.CvActivity;
import com.lyl.mvptest.mvp.eventbus.EventBusActivity;
import com.lyl.mvptest.mvp.jetpack_0701_test.JetPackActivity;
import com.lyl.mvptest.mvp.live_data_bus.LiveEventBusActivity1;
import com.lyl.mvptest.mvp.radar_view.RadarViewActivity;
import com.lyl.mvptest.mvp.recycleview.RecycleViewActivity;
import com.lyl.mvptest.mvp.suefaceview.DrawActivity;
import com.lyl.mvptest.mvp.textclock_view.TextClockViewActivity;
import com.lyl.mvptest.mvp.tree.TreeActivity;

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

    @OnClick({R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9,R.id.btn10})
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
            case R.id.btn5:
                startActivity(new Intent(getContext(), TextClockViewActivity.class));
                break;
            case R.id.btn6:
                startActivity(new Intent(getContext(), RecycleViewActivity.class));
                break;
            case R.id.btn7:
                startActivity(new Intent(getContext(), JetPackActivity.class));
                break;
            case R.id.btn8:
                startActivity(new Intent(getContext(), LiveEventBusActivity1.class));
                break;
            case R.id.btn9:
                startActivity(new Intent(getContext(), EventBusActivity.class));
                break;
            case R.id.btn10:
                startActivity(new Intent(getContext(), TreeActivity.class));
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
