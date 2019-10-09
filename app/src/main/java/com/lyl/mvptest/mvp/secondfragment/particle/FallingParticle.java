package com.lyl.mvptest.mvp.secondfragment.particle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Create By: lyl
 * Date: 2019-10-09 15:16
 */
class FallingParticle extends Particle {

    float radius = FallingParticleFactory.PART_WH;
    float alpha = 1.0f;
    Rect mBound;

    public FallingParticle(float cx, float cy, int color, Rect bound) {
        super(cx, cy, color);
        mBound = bound;
    }

    @Override
    protected void calculate(float factor) {
        cx = cx + factor * Utils.RANDOM.nextInt(mBound.width()) * (Utils.RANDOM.nextFloat() - 0.5f);
        cy = cy + factor * Utils.RANDOM.nextInt(mBound.height() / 2);
        radius = radius - factor * Utils.RANDOM.nextInt(2);
        alpha = (1f - factor) * (1 + Utils.RANDOM.nextFloat());
    }

    @Override
    protected void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        paint.setAlpha((int) (Color.alpha(color) * alpha));
        canvas.drawCircle(cx, cy, radius / 2, paint);
    }
}
