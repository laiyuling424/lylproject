package com.lyl.core.simple1;

import android.os.Bundle;
import android.util.Log;

import com.lyl.core.base.BaseActivity;

/**
 * Create By: lyl
 * Date: 2019-11-28 10:02
 */

public class LoginActivity extends BaseActivity<ILoginView, LoginPresenter<ILoginView>> implements ILoginView {

    LoginPresenter presenter;

    @Override
    protected LoginPresenter createPresenter() {
        presenter = new LoginPresenter(this);
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.login();
    }

    @Override
    public String getphone() {
        return "phonenumber";
    }

    @Override
    public String getUserName() {
        return "UserName";
    }

    @Override
    public String getPassWord() {
        return "PassWord";
    }

    @Override
    public void showError(String error) {
        Log.d("lyl", "showError  error=" + error);
    }

    @Override
    public void showLoading() {
        Log.d("lyl", "showLoading");
    }

    @Override
    public void stopLoading() {
        Log.d("lyl", "stopLoading");
    }

    @Override
    public void showNetworkError() {
        Log.d("lyl", "showNetworkError");
    }

    @Override
    public boolean isNetworkAvailable() {
        return true;
    }

    @Override
    public void loadSuccess() {
        Log.d("lyl", "loadSuccess");
    }

    @Override
    public void showDataError() {
        Log.d("lyl", "showDataError");
    }


}
