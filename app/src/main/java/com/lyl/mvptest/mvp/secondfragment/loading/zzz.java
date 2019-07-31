package com.lyl.mvptest.mvp.secondfragment.loading;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.Nullable;

/**
 * User: lyl
 * Date: 2019-07-31 09:48
 */
public class zzz extends View {
    public zzz(Context context) {
        super(context);


        ValueAnimator downControl= ValueAnimator.ofFloat(0f,1f);
        downControl.setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public zzz(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public zzz(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public zzz(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
