package com.odbpo.fenggou.orderlist.ui.order;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.odbpo.fenggou.orderlist.R;
import com.odbpo.fenggou.orderlist.bean.OrderListBean;
import com.odbpo.fenggou.orderlist.ui.order.adapter.FAdapter;
import com.odbpo.fenggou.orderlist.utils.ReadAssetsUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OrderListActivity extends AppCompatActivity {
    @Bind(R.id.rv_f)
    RecyclerView rvF;

    private List<OrderListBean.DataBean> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        ButterKnife.bind(this);
        initData();
        initRecyclerView();
    }

    public void initData() {
        String json = ReadAssetsUtil.getJson(this, "OrderList.json");
        Gson gson = new Gson();
        OrderListBean orderListBean = gson.fromJson(json, OrderListBean.class);
        mData.clear();
        mData.addAll(orderListBean.getData());
    }

    public void initRecyclerView() {
        rvF.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvF.setAdapter(new FAdapter(mData));
    }

}
