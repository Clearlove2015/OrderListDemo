package com.odbpo.fenggou.orderlist.utils;

import android.widget.Toast;

import com.odbpo.fenggou.orderlist.App;

/**
 * @author: zc
 * @Time: 2018/3/12 13:37
 * @Desc:
 */
public class AppToast {
    public static void show(String content){
        Toast.makeText(App.context,content,Toast.LENGTH_SHORT).show();
    }
}
