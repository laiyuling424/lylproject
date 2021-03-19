package com.lyl.view.particle;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Create By: lyl
 * Date: 2019-10-09 13:24
 */
public abstract class Particle {

    float cx;
    float cy;
    int color;

    public Particle(float cx, float cy, int color) {
        this.cx = cx;
        this.cy = cy;
        this.color = color;
    }

    protected abstract void calculate(float factor);

    protected abstract void draw(Canvas canvas, Paint paint);

    public void advance(float factor, Canvas canvas, Paint paint) {
        calculate(factor);
        draw(canvas, paint);
    }
}
