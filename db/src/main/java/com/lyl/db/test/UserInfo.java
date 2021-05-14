package com.lyl.db.test;

import com.lyl.db.annotation.DbField;
import com.lyl.db.annotation.DbTable;

/**
 * author : lyl
 * e-mail : laiyuling424@gmail.com
 * date   : 5/14/21 9:49 AM
 */
@DbTable("UserInfo")
public class UserInfo {
    @DbField("user_id")
    private Long userId;
    @DbField("user_name")
    private String userName;
    @DbField("nick_name")
    private String nickName;
    @DbField("avatar")
    private String avatar;
    @DbField("mark_name")
    private String markName;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
