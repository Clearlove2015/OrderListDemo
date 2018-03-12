package com.odbpo.fenggou.orderlist.utils;

import java.text.DecimalFormat;

/**
 * @author: zc
 * @Time: 2018/3/12 10:37
 * @Desc:
 */
public class DataFormat {

    //保留两位小数
    public static String getPrice(double price) {
        DecimalFormat df = new DecimalFormat("######0.00");
        return "¥" + df.format(price);
    }

}
