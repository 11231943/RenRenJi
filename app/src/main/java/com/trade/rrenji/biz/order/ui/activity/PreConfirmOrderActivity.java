package com.trade.rrenji.biz.order.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.goods.GoodsDetailBean;
import com.trade.rrenji.bean.goods.NetAccessoryListBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.order.presenter.AccessoryInfoPresenter;
import com.trade.rrenji.biz.order.presenter.AccessoryInfoPresenterImpl;
import com.trade.rrenji.biz.order.ui.view.AccessoryInfoView;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.GlideUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.pre_confirm_layout)
public class PreConfirmOrderActivity extends BaseActivity implements AccessoryInfoView {

    @ViewInject(R.id.order_image)
    ImageView order_image;
    @ViewInject(R.id.order_name)
    TextView order_name;
    @ViewInject(R.id.order_color)
    TextView order_color;
    @ViewInject(R.id.order_size)
    TextView order_size;
    @ViewInject(R.id.order_price)
    TextView order_price;
    @ViewInject(R.id.order_mun)
    TextView order_mun;
    @ViewInject(R.id.pre_order_sum)
    TextView pre_order_sum;

    @ViewInject(R.id.order_sum_price)
    TextView order_sum_price;

    @ViewInject(R.id.confirm_btn)
    TextView confirm_btn;

    @ViewInject(R.id.recycler_view)
    RecyclerView recyclerView;

    AccessoryInfoPresenter mPresenter;
    GoodsDetailBean mGoodsDetailBean;
    AccessoryInfoAdapter mAccessoryInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("确认订单");
        mGoodsDetailBean = (GoodsDetailBean) getIntent().getSerializableExtra("mNetGoodsDetailBean");
        initData();
    }

    private void initData() {
        GlideUtils.getInstance().loadIcon(this, mGoodsDetailBean.getGoodsCoverImg(), R.drawable.ic_launcher, order_image);
        order_name.setText(mGoodsDetailBean.getTitle());
        order_price.setText("￥" + mGoodsDetailBean.getPrice());
        order_sum_price.setText("￥" + mGoodsDetailBean.getOriginalPrice());
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreConfirmOrderActivity.this, PayConfirmOrderActivity.class);
                intent.putExtra("GoodsDetailBean", mGoodsDetailBean);
                intent.putExtra("accessoryList", (Serializable) mAccessoryInfoAdapter.getAccessoryCheckBox());
                startActivity(intent);
            }
        });
        mPresenter.getAccessoryInfo(this, mGoodsDetailBean.getGoodsCode());

    }

    @Override
    public void getAccessoryInfoSuccess(NetAccessoryListBean netAccessoryListBean) {
        mAccessoryInfoAdapter = new AccessoryInfoAdapter(this);
        recyclerView.addItemDecoration(new LinearSpacingDecoration(20, 0));
        recyclerView.setAdapter(mAccessoryInfoAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        mAccessoryInfoAdapter.addAll(netAccessoryListBean.getData().getResultList());
        mAccessoryInfoAdapter.notifyDataSetChanged();
    }

    @Override
    public void getAccessoryInfoError(int code, String msg) {

    }

    @Override
    protected void attachPresenter() {
        mPresenter = new AccessoryInfoPresenterImpl(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
    }

    /**
     * 配件适配器
     */
    public class AccessoryInfoAdapter extends RecyclerListAdapter<NetAccessoryListBean.DataBean.ResultListBean> {

        private Activity mContext;

        private List<NetAccessoryListBean.DataBean.ResultListBean> mAccessoryList;

        public OnClickListener mOnClickListener;

        public void setOnClickListener(OnClickListener mOnClickListener) {
            this.mOnClickListener = mOnClickListener;
        }

        private List<NetAccessoryListBean.DataBean.ResultListBean> getAccessoryCheckBox() {
            if (mAccessoryList != null) {
                mAccessoryList.clear();
            }
            for (NetAccessoryListBean.DataBean.ResultListBean bean : getDataSet()) {
                if (bean.isCheck()) {
                    mAccessoryList.add(bean);
                }
            }
            return mAccessoryList;
        }

        public AccessoryInfoAdapter(Activity context) {
            super(context);
            mContext = context;
            mAccessoryList = new ArrayList<NetAccessoryListBean.DataBean.ResultListBean>();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.accessory_item, parent, false);
            return new AccessoryViewHolder(view);
        }

        public class AccessoryViewHolder extends ViewHolder {
            TextView accessory_text;
            TextView accessory_price;
            ImageView accessory_image;
            CheckBox accessory_check;

            public AccessoryViewHolder(View itemView) {
                super(itemView);
                accessory_image = (ImageView) itemView.findViewById(R.id.accessory_image);
                accessory_text = (TextView) itemView.findViewById(R.id.accessory_text);
                accessory_price = (TextView) itemView.findViewById(R.id.accessory_price);
                accessory_check = (CheckBox) itemView.findViewById(R.id.accessory_check);
            }

            @Override
            public void bindData(final NetAccessoryListBean.DataBean.ResultListBean data, final int position) {
                super.bindData(data, position);
                if (data.isCheck()) {
                    accessory_check.setChecked(true);
                } else {
                    accessory_check.setChecked(false);
                }
                GlideUtils.getInstance().loadIcon(mContext, data.getUrl(), R.drawable.ic_launcher, accessory_image);
                accessory_text.setText(data.getAccessoryName());
                accessory_price.setText("￥" + data.getPrice());
                accessory_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!accessory_check.isChecked()) {
                            accessory_check.setChecked(true);
                            data.setCheck(true);
                        } else {
                            accessory_check.setChecked(false);
                            data.setCheck(false);
                        }
                    }
                });
            }
        }
    }

    public interface OnClickListener {
        void OnItemClick(NetAccessoryListBean.DataBean.ResultListBean data, int position);
    }
}
