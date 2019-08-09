package com.lyl.wanandroid.http.cookie;

import com.lyl.wanandroid.util.MyLog;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * User: lyl
 * Date: 2019-07-31 15:24
 */
public class CookieJarImpl implements CookieJar {

    private CookieStore cookieStore;

    HttpUrl urll= HttpUrl.parse("https://www.wanandroid.com");

    public CookieJarImpl(CookieStore cookieStore) {
        if(cookieStore == null) {
            throw new IllegalArgumentException("cookieStore can not be null.");
        }
        this.cookieStore = cookieStore;
    }

    @Override
    public synchronized void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        this.cookieStore.add(urll, cookies);
    }

    @Override
    public synchronized List<Cookie> loadForRequest(HttpUrl url) {
        MyLog.INSTANCE.Logd("this is cookie:"+this.cookieStore.get(urll));
        return this.cookieStore.get(urll);
    }

    public CookieStore getCookieStore() {
        return this.cookieStore;
    }
}

