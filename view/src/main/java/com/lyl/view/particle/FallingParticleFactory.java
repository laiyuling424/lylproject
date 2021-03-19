package com.lyl.view.particle;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

/**
 * Create By: lyl
 * Date: 2019-10-09 15:07
 */
public class FallingParticleFactory extends ParticleFactory {

    public static final int PART_WH = 14;

    @Override
    public Particle[][] generateParticle(Bitmap bitmap, Rect bound) {
        int w = bound.width();
        int h = bound.height();

        int partW_count = w / PART_WH;
        int partH_count = h / PART_WH;
        partW_count = partW_count > 0 ? partW_count : 1;
        partH_count = partH_count > 0 ? partH_count : 1;

        int bitmap_part_w = bitmap.getWidth() / partW_count;
        int bitmap_part_h = bitmap.getHeight() / partH_count;

        Particle[][] particles = new Particle[partH_count][partW_count];
        for (int row = 0; row < partH_count; row++) {
            for (int column = 0; column < partW_count; column++) {
                Log.d("lyll", "row--->" + row + "   column--->" + column);
                int color = bitmap.getPixel(column * bitmap_part_w, row * bitmap_part_h);
                float x = bound.left + PART_WH * column;
                float y = bound.top + PART_WH * row;
                particles[row][column] = new FallingParticle(x, y, color, bound);
            }
        }
        return particles;
    }
}
