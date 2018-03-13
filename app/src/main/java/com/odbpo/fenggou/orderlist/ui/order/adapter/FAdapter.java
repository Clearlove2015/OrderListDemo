package com.odbpo.fenggou.orderlist.ui.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.odbpo.fenggou.orderlist.R;
import com.odbpo.fenggou.orderlist.bean.OrderListBean;
import com.odbpo.fenggou.orderlist.utils.AppToast;
import com.odbpo.fenggou.orderlist.utils.DataFormat;
import com.odbpo.fenggou.orderlist.widget.NoScrollLinearLayoutManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: zc
 * @Time: 2018/3/12 9:33
 * @Desc:
 */
public class FAdapter extends RecyclerView.Adapter {
    private List<OrderListBean.DataBean> mData;
    private Context context;

    public FAdapter(List<OrderListBean.DataBean> mData) {
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_rv_f, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemHolder = (ItemViewHolder) holder;
        OrderListBean.DataBean bean = mData.get(position);

        itemHolder.tvOrderCode.setText("订单号：" + bean.getOrderCode());
        itemHolder.tvOrderStatus.setText(setDefaltOrderStatus(Integer.parseInt(bean.getOrderStatus()), bean.getOrderLinePay(), Integer.parseInt(bean.getEvaluateFlag())));
        NoScrollLinearLayoutManager layoutManager = new NoScrollLinearLayoutManager(context);
        layoutManager.setScrollEnabled(false);
        itemHolder.rvS.setLayoutManager(layoutManager);
        itemHolder.rvS.setAdapter(new SAdapter(bean.getOrderGoodsList()));
        itemHolder.tvOrderPrice.setText(DataFormat.getPrice(bean.getOrderPrice()));

        //itemHolder.setIsRecyclable(false);//取消复用，解决recyclerview中ItemViewHolder复用数据错乱问题
        //数据复用问题已解决-->原因：其实不是复用的问题，是因为TextView显示的代码没写全（tvFirst.setVisibility(View.VISIBLE);），偷了下懒。
        setDefaultBtnText(itemHolder, bean);

        itemHolder.itemView.setOnClickListener(new OnItemClickListener("ItemView", bean));
        itemHolder.tvFirst.setOnClickListener(new OnItemClickListener(itemHolder.tvFirst.getText().toString(), bean));
        itemHolder.tvSecond.setOnClickListener(new OnItemClickListener(itemHolder.tvSecond.getText().toString(), bean));
        itemHolder.tvThird.setOnClickListener(new OnItemClickListener(itemHolder.tvThird.getText().toString(), bean));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.itemView)
        LinearLayout itemView;
        @Bind(R.id.tv_orderCode)
        TextView tvOrderCode;
        @Bind(R.id.tv_orderStatus)
        TextView tvOrderStatus;
        @Bind(R.id.rv_s)
        RecyclerView rvS;
        @Bind(R.id.tv_orderPrice)
        TextView tvOrderPrice;
        @Bind(R.id.tv_first)
        TextView tvFirst;
        @Bind(R.id.tv_second)
        TextView tvSecond;
        @Bind(R.id.tv_third)
        TextView tvThird;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //其他订单状态
    public String setDefaltOrderStatus(int orderStatus, int orderLinePay, int evaluateFlag) {
        String tempStr = " ";
        if (orderStatus == 0) {
            if (orderLinePay == 0) {
                tempStr = "等待发货";
            } else {
                tempStr = "等待付款";
            }
        } else if (orderStatus == 1 || orderStatus == 5 || orderStatus == 6) {
            tempStr = "等待发货";
        } else if (orderStatus == 2) {
            tempStr = "等待收货";
        } else if (orderStatus == 3) {
            if (evaluateFlag == 1) {
                tempStr = "交易完成";
            } else {
                tempStr = "等待评价";
            }
        } else if (orderStatus == 4) {
            tempStr = "交易取消";
        } else if (orderStatus == 7) {
            tempStr = "待审核";
        } else if (orderStatus == 8) {
            tempStr = "待填写物流信息";
        } else if (orderStatus == 9) {
            tempStr = "审核未通过";
        } else if (orderStatus == 10) {
            tempStr = "待商家发货";
        } else if (orderStatus == 11) {
            tempStr = "退单完成";
        } else if (orderStatus == 12) {
            tempStr = "同意退款";
        } else if (orderStatus == 13) {
            tempStr = "拒绝退款";
        } else if (orderStatus == 14) {
            tempStr = "待审核";
        } else if (orderStatus == 15) {
            tempStr = "待审核";
        } else if (orderStatus == 16) {
            tempStr = "拒绝收货";
        } else if (orderStatus == 17) {
            tempStr = "退单完成";
        } else if (orderStatus == 18) {
            tempStr = "退单完成";
        } else if (orderStatus == 19) {
            tempStr = "退单完成";
        } else if (orderStatus == 20) {
            tempStr = "待退款";
        } else if (orderStatus == 21) {
            tempStr = "待退款";
        } else if (orderStatus == 22) {
            tempStr = "审核未通过";
        }

        return tempStr;
    }

    //其他订单按钮
    private void setDefaultBtnText(ItemViewHolder holder, OrderListBean.DataBean bean) {
        int orderStatus = Integer.parseInt(bean.getOrderStatus());
        int evaluateFlag = Integer.parseInt(bean.getEvaluateFlag());
        int isBack = bean.getIsBack();
        int orderExpressType = Integer.parseInt(bean.getOrderExpressType());
        TextView tvFirst = holder.tvFirst;
        TextView tvSecond = holder.tvSecond;

        if (orderStatus == 0) {
            tvFirst.setVisibility(View.VISIBLE);
            tvSecond.setVisibility(View.VISIBLE);
            tvFirst.setText("立即支付");
            tvSecond.setText("取消订单");
        } else if (orderStatus == 2) {
            if (orderExpressType == 1) {
                tvFirst.setVisibility(View.GONE);
                tvSecond.setVisibility(View.VISIBLE);
                tvSecond.setText("确认收货");
            } else {
                tvFirst.setVisibility(View.VISIBLE);
                tvSecond.setVisibility(View.VISIBLE);
                tvFirst.setText("查看物流");
                tvSecond.setText("确认收货");
            }
        } else if (orderStatus == 3) {
            if (evaluateFlag == 1) {
                tvFirst.setVisibility(View.GONE);
                tvSecond.setVisibility(View.VISIBLE);
                tvSecond.setText("申请退货");
            } else {
                tvFirst.setVisibility(View.VISIBLE);
                tvSecond.setVisibility(View.VISIBLE);
                tvFirst.setText("评价");
                tvSecond.setText("申请退货");
            }
        } else if (orderStatus == 13) {
            if (isBack == 2) {
                tvFirst.setVisibility(View.GONE);
                tvSecond.setVisibility(View.VISIBLE);
                tvSecond.setText("退款详情");
            } else {
                tvFirst.setVisibility(View.GONE);
                tvSecond.setVisibility(View.VISIBLE);
                tvSecond.setText("退货详情");
            }
        } else if (orderStatus == 15 || orderStatus == 18) {
            tvFirst.setVisibility(View.GONE);
            tvSecond.setVisibility(View.VISIBLE);
            tvSecond.setText("退款详情");
        } else if (orderStatus == 8) {
            tvFirst.setVisibility(View.VISIBLE);
            tvSecond.setVisibility(View.VISIBLE);
            tvFirst.setText("填写物流");
            tvSecond.setText("退货详情");
        } else if (orderStatus == 7 || orderStatus == 8 || orderStatus == 9 || orderStatus == 10 || orderStatus == 11 || orderStatus == 14 || orderStatus == 16 || orderStatus == 17 || orderStatus == 12) {
            if (orderStatus == 8) {
                tvFirst.setVisibility(View.VISIBLE);
                tvSecond.setVisibility(View.VISIBLE);
                tvFirst.setText("物流信息");
                tvSecond.setText("退货详情");
            } else {
                tvFirst.setVisibility(View.GONE);
                tvSecond.setVisibility(View.VISIBLE);
                tvSecond.setText("退货详情");
            }
        } else if (orderStatus == 5 || orderStatus == 6) {
            tvFirst.setVisibility(View.GONE);
            tvSecond.setVisibility(View.VISIBLE);
            tvSecond.setText("申请退货");
        } else if (orderStatus == 1) {
            tvFirst.setVisibility(View.GONE);
            tvSecond.setVisibility(View.VISIBLE);
            tvSecond.setText("申请退款");
        } else if (orderStatus == 18) {
            tvFirst.setVisibility(View.GONE);
            tvSecond.setVisibility(View.VISIBLE);
            tvSecond.setText("退款详情");
        } else {
            tvFirst.setVisibility(View.GONE);
            tvSecond.setVisibility(View.GONE);
        }
    }

    class OnItemClickListener implements View.OnClickListener {
        String tempStr;//按钮文字
        OrderListBean.DataBean bean;
        Gson gson = new Gson();

        public OnItemClickListener(String tempStr, OrderListBean.DataBean bean) {
            this.tempStr = tempStr;
            this.bean = bean;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.itemView:
                    AppToast.show(tempStr + "\n" + gson.toJson(bean));
                    break;
                case R.id.tv_first:
                    AppToast.show(tempStr + "\n" + gson.toJson(bean));
                    break;
                case R.id.tv_second:
                    AppToast.show(tempStr + "\n" + gson.toJson(bean));
                    break;
                case R.id.tv_third:
                    AppToast.show(tempStr + "\n" + gson.toJson(bean));
                    break;
            }
        }
    }

}
