package com.lyl.view.particle;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Create By: lyl
 * Date: 2019-10-09 13:27
 */
public abstract class ParticleFactory {

    public abstract Particle[][] generateParticle(Bitmap bitmap, Rect bound);
}
