package com.lyl.mvptest.mvp.secondfragment.particle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lyl.mvptest.R;

public class ParticleExplosionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particle_animations);

        ExplosionField explosionField = new ExplosionField(this, new FallingParticleFactory());
        explosionField.addListener(findViewById(R.id.content));
    }
}
