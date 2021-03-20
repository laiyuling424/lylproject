package com.lyl.core.mvpbase.simple1;

import com.lyl.core.mvpbase.BaseView;

/**
 * Create By: lyl
 * Date: 2019-11-28 09:59
 */
public interface ILoginView extends BaseView {
    String getphone();//手机号

    String getUserName();//用户名

    String getPassWord();//获取密码
}
