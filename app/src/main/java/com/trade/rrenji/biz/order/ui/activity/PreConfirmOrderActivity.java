package com.trade.rrenji.biz.order.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gelitenight.superrecyclerview.LinearSpacingDecoration;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.goods.GoodsDetailBean;
import com.trade.rrenji.bean.goods.NetAccessoryListBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.order.presenter.AccessoryInfoPresenter;
import com.trade.rrenji.biz.order.presenter.AccessoryInfoPresenterImpl;
import com.trade.rrenji.biz.order.ui.view.AccessoryInfoView;
import com.trade.rrenji.event.order.GoOrderActivityEvent;
import com.trade.rrenji.fragment.RecyclerListAdapter;
import com.trade.rrenji.utils.GlideUtils;
import com.trade.rrenji.utils.ViewUtils;
import com.trade.rrenji.view.CommonPopupWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    @ViewInject(R.id.pay_sum_price2)
    TextView pay_sum_price2;
    @ViewInject(R.id.order_sum_price)
    TextView order_sum_price;
    @ViewInject(R.id.confirm_btn)
    TextView confirm_btn;
    @ViewInject(R.id.recycler_view)
    RecyclerView recyclerView;
    @ViewInject(R.id.main_layout)
    RelativeLayout main_layout;

    AccessoryInfoPresenter mPresenter;
    GoodsDetailBean mGoodsDetailBean;
    AccessoryInfoAdapter mAccessoryInfoAdapter;
    double mSumPrice = 0;
    int mSumCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle("确认订单");
        mGoodsDetailBean = (GoodsDetailBean) getIntent().getSerializableExtra("mNetGoodsDetailBean");
        initPayWindow();
        initData();
        EventBus.getDefault().register(this);
    }

    private void initPayWindow() {
        // create popup window
    }

    private void initData() {
        GlideUtils.getInstance().loadImageUrl(this, mGoodsDetailBean.getGoodsCoverImg(), R.drawable.ic_launcher, order_image);
        order_name.setText(mGoodsDetailBean.getTitle());
        order_price.setText("￥" + mGoodsDetailBean.getPrice());
        order_sum_price.setText("￥" + mGoodsDetailBean.getPrice());
        pay_sum_price2.setText("￥" + mGoodsDetailBean.getPrice());
        mSumPrice = mGoodsDetailBean.getPrice();
        mSumCount = 1;
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreConfirmOrderActivity.this, PayConfirmOrderActivity.class);
                intent.putExtra("GoodsDetailBean", mGoodsDetailBean);
                intent.putExtra("accessoryList", (Serializable) mAccessoryInfoAdapter.getAccessoryCheckBox());
                intent.putExtra("mSumPrice", mSumPrice);
                intent.putExtra("mSumCount", mSumCount);
                startActivity(intent);
            }
        });
        pre_order_sum.setText(getString(R.string.pre_order_mun, 1));
        mPresenter.getAccessoryInfo(this, mGoodsDetailBean.getGoodsCode());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(GoOrderActivityEvent event) {
        finish();
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
        mAccessoryInfoAdapter.setOnClickListener(new OnClickListener() {
            @Override
            public void OnItemClick(NetAccessoryListBean.DataBean.ResultListBean data, int type) {
                double tempPrice = 0;
                for (NetAccessoryListBean.DataBean.ResultListBean bean : mAccessoryInfoAdapter.getAccessoryCheckBox()) {
                    tempPrice += bean.getPrice();
                }
                order_sum_price.setText("￥" + (mGoodsDetailBean.getPrice() + tempPrice));
                pay_sum_price2.setText("￥" + (mGoodsDetailBean.getPrice() + tempPrice));
                mSumPrice = mGoodsDetailBean.getPrice() + tempPrice;
                pre_order_sum.setText(getString(R.string.pre_order_mun, (1 + mAccessoryInfoAdapter.getAccessoryCheckBox().size())));
                mSumCount = 1 + mAccessoryInfoAdapter.getAccessoryCheckBox().size();
            }
        });

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
                for (int i = 0; i < mAccessoryList.size(); i++) {
                    if (mAccessoryList.get(position).getAccessoryId() == data.getAccessoryId()) {
                        if (mAccessoryList.get(position).isCheck()) {
                            accessory_check.setChecked(true);
                        } else {
                            accessory_check.setChecked(false);
                        }
                    }
                }
                GlideUtils.getInstance().loadImageUrl(mContext, data.getUrl(), R.drawable.ic_launcher, accessory_image);
                accessory_text.setText(data.getAccessoryName());
                accessory_price.setText("￥" + data.getPrice());
                accessory_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!data.isCheck()) {
                            accessory_check.setChecked(true);
                            data.setCheck(true);
                            mAccessoryList.add(data);
                            if (mOnClickListener != null) {
                                mOnClickListener.OnItemClick(data, 1);
                            }
                        } else {
                            accessory_check.setChecked(false);
                            data.setCheck(false);
                            for (int i = 0; i < mAccessoryList.size(); i++) {
                                if (mAccessoryList.get(i).getAccessoryId() == data.getAccessoryId()) {
                                    mAccessoryList.remove(i);
                                    break;
                                }
                            }
                            if (mOnClickListener != null) {
                                mOnClickListener.OnItemClick(data, 0);
                            }
                        }
                    }
                });
            }
        }
    }

    public interface OnClickListener {
        void OnItemClick(NetAccessoryListBean.DataBean.ResultListBean data, int type);
    }
}
