package com.odbpo.fenggou.orderlist.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * @author: zc
 * @Time: 2018/3/12 15:07
 * @Desc: 禁止RecyclerView滑动
 * http://blog.csdn.net/lengqi0101/article/details/52874762
 */
public class NoScrollLinearLayoutManager extends LinearLayoutManager {
    private boolean isScrollEnabled = true;

    public NoScrollLinearLayoutManager(Context context) {
        super(context);
    }

    public NoScrollLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public NoScrollLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}
