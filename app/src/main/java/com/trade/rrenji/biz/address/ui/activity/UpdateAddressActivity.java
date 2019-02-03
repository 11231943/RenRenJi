package com.trade.rrenji.biz.address.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.address.AddressUpdateBean;
import com.trade.rrenji.bean.address.NetAddressBean;
import com.trade.rrenji.bean.address.UserAddressCurd;
import com.trade.rrenji.biz.address.picker.AddressPicker;
import com.trade.rrenji.biz.address.presenter.UpdateAddressActivityPresenter;
import com.trade.rrenji.biz.address.presenter.UpdateAddressActivityPresenterImpl;
import com.trade.rrenji.biz.address.ui.view.UpdateActivityView;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.utils.AssetsUtils;
import com.trade.rrenji.utils.CollectionUtils;
import com.trade.rrenji.utils.GsonUtils;
import com.trade.rrenji.utils.ThreadPoolManager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.address_add)
public class UpdateAddressActivity extends BaseActivity implements UpdateActivityView {

    public static final int TYPE_ONCREATE = 0;
    public static final int TYPE_UPDATE = 1;

    @ViewInject(R.id.address_name)
    EditText address_name;
    @ViewInject(R.id.address_phone)
    EditText address_phone;
    @ViewInject(R.id.address)
    TextView address;
    @ViewInject(R.id.address_info)
    EditText address_info;

    @ViewInject(R.id.delete_btn)
    TextView delete_btn;

    String mProvince;
    String mCity;
    String mCounty;

    private int type = 0;

    private NetAddressBean.ResultBean.AddressListBean bean = null;

    String addressId = "";
    private AddressPicker mAddressPicker;
    private ArrayList<AddressPicker.Province> mData;
    private boolean isSave = true;

    UpdateAddressActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        if (type == TYPE_UPDATE) {
            bean = getIntent().getParcelableExtra("address");
            if (bean == null) return;
            addressId = bean.getAddressId();
        }
        initView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_address, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_address:
                updateAddress();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateAddress() {
        UserAddressCurd userAddressCurd = new UserAddressCurd();
        userAddressCurd.setAddressId(addressId);
        userAddressCurd.setCity(mCity);
        userAddressCurd.setConsigneeName(address_name.getText().toString().trim());
        userAddressCurd.setConsigneeTel(address_phone.getText().toString().trim());
        userAddressCurd.setDistrict(mCounty);
        userAddressCurd.setLocation(address_info.getText().toString().trim());
        userAddressCurd.setProvince(mProvince);
        mPresenter.updateAddress(this, type, userAddressCurd);
    }

    private void initView() {
        if (bean != null) {
            address_name.setText(bean.getConsigneeName());
            address_name.setSelection(bean.getConsigneeName().length());
            address_phone.setText(bean.getConsigneeTel());
            address.setText(getString(R.string.address_show, bean.getProvince(), bean.getCity(), bean.getDistrict()));
            address_info.setText(bean.getLocation());
            mProvince = bean.getProvince();
            mCity = bean.getCity();
            mCounty = bean.getDistrict();
        }
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAddressPicker != null) {
                    mAddressPicker.show();
                }
            }
        });
        mAddressPicker = new AddressPicker(this);
        mAddressPicker.setTopLineVisible(false);
        mAddressPicker.setTextColor(0xFFFE5252, 0xFF999999);
        mAddressPicker.setLineColor(0xFFFE5252);
        mAddressPicker.setTextSize(12);
        mAddressPicker.setOnAddressPickListener(
                new AddressPicker.OnAddressPickListener() {
                    @Override
                    public void onAddressPicked(String province, String city,
                                                String county) {
                        mProvince = province;
                        mCity = city;
                        mCounty = county;
                        address.setText(getString(R.string.address_show, province, city,
                                county));
                    }
                });
        ThreadPoolManager.getInstance().addTask(new MyRunnable());

    }

    private void initActionBar() {
        if (type == TYPE_ONCREATE) {
            setActionBarTitle(R.string.add_title_address);
        } else {
            setActionBarTitle(R.string.update_title_address);
        }
    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            String json = AssetsUtils.readText(UpdateAddressActivity.this, "city.json");
            mData = GsonUtils.getGson().fromJson(json,
                    new TypeToken<ArrayList<AddressPicker.Province>>() {
                    }.getType());

            if (!CollectionUtils.isEmpty(mData)) {
                mAddressPicker.init(mData);
            }
        }
    }


    @Override
    protected void attachPresenter() {
        mPresenter = new UpdateAddressActivityPresenterImpl(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    public void updateAddressSuccess(AddressUpdateBean updateBean) {

    }

    @Override
    public void updateAddressError(int code, String msg) {

    }
}
