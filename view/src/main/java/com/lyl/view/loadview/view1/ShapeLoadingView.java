package com.lyl.view.loadview.view1;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Create By: lyl
 * Date: 2019-11-22 16:26
 */
@SuppressLint("AppCompatCustomView")
public class ShapeLoadingView extends ImageView {

    CureentShape mCureentShape = CureentShape.SHAPE_RECT;
    ObjectAnimator mRectRoteAnimation, mDefaultRoteAnimation;

    public ShapeLoadingView(Context context) {
        this(context, null);
    }

    public ShapeLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initRoteAnimation();
    }

    public void changeShape() {
        if (mCureentShape == CureentShape.SHAPE_RECT) {
            mCureentShape = CureentShape.SHAPE_TRINGLE;
        } else if (mCureentShape == CureentShape.SHAPE_TRINGLE) {
            mCureentShape = CureentShape.SHAPE_CIRCLE;
        } else if (mCureentShape == CureentShape.SHAPE_CIRCLE) {
            mCureentShape = CureentShape.SHAPE_RECT;
        }
    }

    private void initRoteAnimation() {
        mRectRoteAnimation = ObjectAnimator.ofFloat(this,
                "rotation", 0, -120);
        mDefaultRoteAnimation = ObjectAnimator.ofFloat(this,
                "rotation", 0, 180);
    }

    public ObjectAnimator getUpThrowRoteAnimation() {
        switch (mCureentShape) {
            case SHAPE_RECT:
                return mRectRoteAnimation;
            default:
                return mDefaultRoteAnimation;
        }
    }


    enum CureentShape {SHAPE_RECT, SHAPE_TRINGLE, SHAPE_CIRCLE}


}
