package com.odbpo.fenggou.orderlist;

import android.app.Application;
import android.content.Context;

/**
 * @author: zc
 * @Time: 2018/3/12 13:38
 * @Desc:
 */
public class App extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
