package com.lyl.mvptest.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * create 2018/8/21
 * author lyl
 */
public class OkhttpUtil {
    public static OkHttpClient okHttpClient;
    public static void GetOkhttp(String url, okhttp3.Callback callback){
        //String url = "http://wwww.baidu.com";
        okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                
                .get()//默认就是GET请求，可以不写
                .build();
        okHttpClient.newCall(request).enqueue(callback);
/*        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(getClass().getSimpleName(), "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(getClass().getSimpleName(), "onResponse: " + response.body().string());
            }
        });*/
    }
}
