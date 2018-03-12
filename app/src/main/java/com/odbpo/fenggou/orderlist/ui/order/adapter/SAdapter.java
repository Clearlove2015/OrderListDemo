package com.odbpo.fenggou.orderlist.ui.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.odbpo.fenggou.orderlist.R;
import com.odbpo.fenggou.orderlist.bean.OrderListBean;
import com.odbpo.fenggou.orderlist.utils.DataFormat;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: zc
 * @Time: 2018/3/12 10:29
 * @Desc:
 */
public class SAdapter extends RecyclerView.Adapter {
    private List<OrderListBean.DataBean.OrderGoodsListBean> mData;
    private Context context;

    public SAdapter(List<OrderListBean.DataBean.OrderGoodsListBean> mData) {
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_rv_s, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemHolder = (ItemViewHolder) holder;
        OrderListBean.DataBean.OrderGoodsListBean bean = mData.get(position);
        Glide.with(context).load(bean.getGoodsImg()).into(itemHolder.ivImage);
        itemHolder.tvGoodsName.setText(bean.getGoodsName());
        itemHolder.tvGoodsInfoNum.setText("x" + bean.getGoodsInfoNum());
        itemHolder.tvGoodsInfoOldPrice.setText(DataFormat.getPrice(bean.getGoodsInfoPrice()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_image)
        ImageView ivImage;
        @Bind(R.id.tv_goodsName)
        TextView tvGoodsName;
        @Bind(R.id.tv_goodsInfoNum)
        TextView tvGoodsInfoNum;
        @Bind(R.id.tv_goodsInfoOldPrice)
        TextView tvGoodsInfoOldPrice;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
