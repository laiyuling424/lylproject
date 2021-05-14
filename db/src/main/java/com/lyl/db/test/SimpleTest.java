package com.lyl.db.test;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.lyl.db.db.DaoFactory;
import com.lyl.db.sub_db.SubDaoFactory;
import com.lyl.db.sub_db.User;
import com.lyl.db.sub_db.UserDao;

/**
 * author : lyl
 * e-mail : laiyuling424@gmail.com
 * date   : 5/14/21 9:46 AM
 */
class SimpleTest {

    UserDao userDao;
    Context context;

    protected void onCreate(Bundle savedInstanceState) {

        DaoFactory.getInstance().init(context, "dbTest.db");

        userDao = DaoFactory.getInstance().getBaseDao(UserDao.class, User.class);

        User currentUser = userDao.getCurrentUser();

        System.out.println("当前登录用户：" + currentUser.getName());

    }


    // 测试登陆和切换用户
    public void onLogin1(View view) {
        User user = new User();
        user.setPassword("123456");
        user.setName("用户1");
        user.setId("n0001");
        userDao.insert(user);
        // 显示登录用户
        User currentUser = userDao.getCurrentUser();
        System.out.println("当前登录用户：" + currentUser.getName());
    }

    public void onLogin2(View view) {
        User user = new User();
        user.setPassword("123456");
        user.setName("用户2");
        user.setId("n0002");
        userDao.insert(user);
        User currentUser = userDao.getCurrentUser();
        System.out.println("当前登录用户：" + currentUser.getName());
    }


    // 设置插入数据
    public void onSubInsert(View view) {
        SubDaoFactory.getInstance().init(context, "dbTest.db");
        UserInfoDao photoDao = SubDaoFactory.getInstance().getSubDao(UserInfoDao.class, UserInfo.class);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(123456L);
        userInfo.setUserName("N1234");
        userInfo.setNickName("ddssingsong");
        userInfo.setMarkName("mark");
        photoDao.insert(userInfo);
    }

}
