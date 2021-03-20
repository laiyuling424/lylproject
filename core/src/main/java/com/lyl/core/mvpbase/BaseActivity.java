package com.lyl.core.mvpbase;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Create By: lyl
 * Date: 2019-11-28 09:51
 */

public abstract class BaseActivity<V extends BaseView, T extends BasePresenter<V>> extends AppCompatActivity {
    protected T presenter;

    protected abstract T createPresenter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView((V) this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
