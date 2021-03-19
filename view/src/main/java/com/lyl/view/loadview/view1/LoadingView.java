package com.lyl.view.loadview.view1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lyl.view.R;

/**
 * Create By: lyl
 * Date: 2019-11-22 16:25
 */
public class LoadingView extends LinearLayout {
    ShapeLoadingView mShapeLodingView;
    AnimatorSet mFreeFallAnimatiorSet, mFreeUpAnimatiorSet;
    int mTranslationYDistance = 270;
    float factor = 2;
    int ANIMATION_DURATION = 520;
    ObjectAnimator mRectRoteAnimation, mDefaultRoteAnimation;
    ShapeLoadingView.CureentShape mCureentShape = ShapeLoadingView.CureentShape.SHAPE_RECT;
    private ImageView mIndicationView;
    private Context mContext;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initLayout();
        init();
    }

    public void init() {
        initFreeFallAnimation();
        upThrow();
//        initRoteAnimation();
//        ObjectAnimator upThrowRoteAnimation = getUpThrowRoteAnimation();
//        upThrowRoteAnimation.setRepeatCount(Animation.INFINITE);
//        upThrowRoteAnimation.start();

    }

    public void start() {
        freeFall();
    }

    public void stop() {
//        ViewGroup parent = (ViewGroup) this.getParent();
//        if(parent != null){
//            parent.removeView(this);
//            mShapeView.clearAnimation();
//            mShadowView.clearAnimation();
//            this.removeAllViews();
//            mStopAnimator = true;
//        }
    }

    public void fall() {
        mFreeFallAnimatiorSet.start();
    }

    public void up() {
        mFreeUpAnimatiorSet.start();
    }

    private void initLayout() {
        View view = View.inflate(mContext, R.layout.load_view, this);
        mShapeLodingView = (ShapeLoadingView) view.findViewById(R.id.shapeLoadingView);
        mIndicationView = (ImageView) view.findViewById(R.id.indication);
    }

    //初始化下落动画
    private void initFreeFallAnimation() {
        // 下落动画集合
        mFreeFallAnimatiorSet = new AnimatorSet();
        // 几何图形的垂直位移动画
        ObjectAnimator freeFallTranslationAnimator = ObjectAnimator.ofFloat(
                mShapeLodingView, "translationY", 0, mTranslationYDistance);
        // 定义动画的变化率。
        freeFallTranslationAnimator.setInterpolator(new AccelerateInterpolator(factor));
        // 中间阴影缩小动画
        ObjectAnimator scaleIndication = ObjectAnimator.ofFloat(mIndicationView,
                "scaleX", 1, 0.2f);

        mFreeFallAnimatiorSet.setDuration(ANIMATION_DURATION);
        mFreeFallAnimatiorSet.playTogether(freeFallTranslationAnimator, scaleIndication);

//        mFreeFallAnimatiorSet.addListener(new AnimatorListenerAdapter() {
//            //设置动画监听器，监听该动画的开始、停止、取消、结束等状态，我们往往会用AnimtorListener适配器类来只实现我们需要的方法
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                // 下落动画结束，改变形状，然后执行上抛动画
////                upThrow();
////                mFreeUpAnimatiorSet.start();
//                mShapeLodingView.changeShape();
//            }
//        });
        mFreeFallAnimatiorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //动画结束,下落
                freeUp();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                // 动画开始，和旋转动画一起执行
                startShapeRoteAnimator();
            }

            /**
             * 执行旋转动画
             */
            private void startShapeRoteAnimator() {
                ObjectAnimator roteAnimation = mShapeLodingView.getUpThrowRoteAnimation();
                roteAnimation.setDuration(ANIMATION_DURATION);
                roteAnimation.setInterpolator(new DecelerateInterpolator(factor));
                roteAnimation.start();
            }
        });


    }

    private void freeFall() {
        mFreeFallAnimatiorSet.start();
    }

    private void freeUp() {
        mFreeUpAnimatiorSet.start();
    }

    private void upThrow() {
        // 下落动画集合
        mFreeUpAnimatiorSet = new AnimatorSet();
        // 几何图形的垂直位移动画
        ObjectAnimator freeUpTranslationAnimator = ObjectAnimator.ofFloat(
                mShapeLodingView, "translationY", mTranslationYDistance, 0);
        // 定义动画的变化率。
        freeUpTranslationAnimator.setInterpolator(new DecelerateInterpolator(factor));
        // 中间阴影缩小动画
        ObjectAnimator scaleIndication = ObjectAnimator.ofFloat(mIndicationView,
                "scaleX", 0.2f, 1);

        mFreeUpAnimatiorSet.setDuration(ANIMATION_DURATION);
        mFreeUpAnimatiorSet.playTogether(freeUpTranslationAnimator, scaleIndication);


        mFreeUpAnimatiorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //动画结束,下落
                freeFall();
            }


            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                // 动画开始，和旋转动画一起执行
                startShapeRoteAnimator();
            }

            /**
             * 执行旋转动画
             */
            private void startShapeRoteAnimator() {
                ObjectAnimator roteAnimation = mShapeLodingView.getUpThrowRoteAnimation();
                roteAnimation.setDuration(ANIMATION_DURATION);
                roteAnimation.setInterpolator(new DecelerateInterpolator(factor));
                roteAnimation.start();
            }
        });


    }


}