package com.lyl.view.particle;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * By: lyl
 * Date: 2019-10-08 11:35
 */
public class ExplosionField extends View {

    private ArrayList<ExplosionAnimator> explosionAnimators;
    private ParticleFactory mParticleFactory;
    private OnClickListener onClickListener;

    public ExplosionField(Context context, ParticleFactory particleFactory) {
        super(context);
        mParticleFactory = particleFactory;
        explosionAnimators = new ArrayList<>();
        attach2Activity();
    }

    private void attach2Activity() {
        ViewGroup decorView = (ViewGroup) ((Activity) getContext()).getWindow().getDecorView();
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        decorView.addView(this);
    }

    public void addListener(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                addListener(viewGroup.getChildAt(i));
            }
        } else {
            view.setClickable(true);
            view.setOnClickListener(getOnClickListener());
        }
    }

    private OnClickListener getOnClickListener() {
        if (onClickListener == null) {
            onClickListener = new OnClickListener() {
                @Override
                public void onClick(View view) {
                    explode(view);
                }
            };
        }
        return onClickListener;
    }

    private void explode(final View view) {
        final Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        if (rect.width() == 0 || rect.height() == 0) {
            return;
        }

        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setTranslationX((Utils.RANDOM.nextFloat() - 0.5f) * view.getWidth() * 0.05f);
                view.setTranslationY((Utils.RANDOM.nextFloat() - 0.5f) * view.getHeight() * 0.05f);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                explode2(view, rect);
            }
        });
        animator.start();
    }

    private void explode2(final View view, Rect rect) {
        final ExplosionAnimator animator = new ExplosionAnimator(this, mParticleFactory, Utils.createBitmapFromView(view), rect);
        explosionAnimators.add(animator);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setClickable(true);
                view.animate().setDuration(150).scaleX(1).scaleY(1).alpha(1).start();
                explosionAnimators.remove(animator);
            }


            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                view.setClickable(false);
                view.animate().setDuration(150).scaleX(0).scaleY(0).alpha(0).start();
            }
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (ExplosionAnimator explosionAnimator : explosionAnimators) {
            explosionAnimator.draw(canvas);
        }
    }
}
