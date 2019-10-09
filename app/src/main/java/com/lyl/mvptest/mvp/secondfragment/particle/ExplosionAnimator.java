package com.lyl.mvptest.mvp.secondfragment.particle;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Create By: lyl
 * Date: 2019-10-09 13:29
 */
public class ExplosionAnimator extends ValueAnimator {

    public static int default_duration = 1500;

    private Particle[][] mParticles;
    private ParticleFactory mParticleFactory;

    private View mContainer;
    private Paint mPaint;

    public ExplosionAnimator(View mContainer, ParticleFactory mParticleFactory, Bitmap bitmap, Rect bound) {
        this.mParticleFactory = mParticleFactory;
        this.mContainer = mContainer;
        mPaint = new Paint();
        setFloatValues(0f, 1f);
        setDuration(default_duration);
        mParticles = this.mParticleFactory.generateParticle(bitmap, bound);
    }

    public void draw(Canvas canvas) {
        if (!isStarted()) {
            return;
        }

        for (Particle[] mParticle : mParticles) {
            for (Particle particle : mParticle) {
                particle.advance((Float) getAnimatedValue(), canvas, mPaint);
            }
        }

        mContainer.invalidate();
    }

    @Override
    public void start() {
        super.start();
        mContainer.invalidate();
    }
}
