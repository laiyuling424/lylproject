package com.lyl.core.mvpbase.simple1;


import android.text.TextUtils;

import com.lyl.core.mvpbase.BasePresenter;
import com.lyl.core.mvpbase.BaseView;

/**
 * Create By: lyl
 * Date: 2019-11-28 09:57
 */
public class LoginPresenter<V extends BaseView> extends BasePresenter<V> {

    private ILoginView view;

    public LoginPresenter(V view) {
        this.view = (ILoginView) view;
    }

    public void login() {
        if (TextUtils.isEmpty(view.getUserName())) {
            view.showError("UserName is empty");
            return;
        }

        if (TextUtils.isEmpty(view.getphone())) {
            view.showError("UserName is empty");
            return;
        }

        if (TextUtils.isEmpty(view.getPassWord())) {
            view.showError("UserName is empty");
            return;
        }

        view.showLoading();
        view.loadSuccess();
        view.stopLoading();


    }

}
