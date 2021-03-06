package com.trade.rrenji.biz.address.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.address.AddressUpdateBean;
import com.trade.rrenji.bean.address.NetAddressBean;
import com.trade.rrenji.bean.address.UserAddressCurd;
import com.trade.rrenji.biz.address.picker.AddressPicker;
import com.trade.rrenji.biz.address.presenter.DelAddressActivityPresenter;
import com.trade.rrenji.biz.address.presenter.UpdateAddressActivityPresenter;
import com.trade.rrenji.biz.address.presenter.UpdateAddressActivityPresenterImpl;
import com.trade.rrenji.biz.address.ui.view.UpdateActivityView;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.base.NetBaseResultBean;
import com.trade.rrenji.event.address.GoAddressActivityEvent;
import com.trade.rrenji.utils.AssetsUtils;
import com.trade.rrenji.utils.CollectionUtils;
import com.trade.rrenji.utils.GsonUtils;
import com.trade.rrenji.utils.ThreadPoolManager;
import com.trade.rrenji.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

@ContentView(R.layout.address_add)
public class UpdateAddressActivity extends BaseActivity implements UpdateActivityView {

    public static final int TYPE_ONCREATE = 0;
    public static final int TYPE_UPDATE = 1;

    String mProvince;
    String mCity;
    String mCounty;
    @Bind(R.id.address_name)
    EditText addressName;
    @Bind(R.id.address_name_icon_go)
    ImageView addressNameIconGo;
    @Bind(R.id.address_name_layout)
    LinearLayout addressNameLayout;
    @Bind(R.id.address_phone)
    EditText addressPhone;
    @Bind(R.id.address_phone_icon_go)
    ImageView addressPhoneIconGo;
    @Bind(R.id.address_phone_layout)
    LinearLayout addressPhoneLayout;
    @Bind(R.id.address)
    TextView address;
    @Bind(R.id.address_img)
    ImageView addressImg;
    @Bind(R.id.address_layout)
    RelativeLayout addressLayout;
    @Bind(R.id.address_address_layout)
    LinearLayout addressAddressLayout;
    @Bind(R.id.address_info)
    EditText addressInfo;
    @Bind(R.id.address_info_layout)
    LinearLayout addressInfoLayout;
    @Bind(R.id.address_default)
    CheckBox addressDefault;
    @Bind(R.id.delete_btn)
    TextView deleteBtn;

    private int mType = 0;

    private NetAddressBean.ResultBean.AddressListBean bean = null;

    String addressId = "";
    private AddressPicker mAddressPicker;
    private ArrayList<AddressPicker.Province> mData;
    private boolean isSave = true;

    UpdateAddressActivityPresenter mPresenter;
    DelAddressActivityPresenter mDelAddressActivityPresenter;

    Handler mUiHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_add);
        ButterKnife.bind(this);
        initActionBar();
        mType = getIntent().getIntExtra("type", -1);
        if (mType == TYPE_UPDATE) {
            bean = (NetAddressBean.ResultBean.AddressListBean) getIntent().getSerializableExtra("address");
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
                if (check()) {
                    updateAddress();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    boolean check() {

        String name = addressName.getText().toString();
        if (null == name || "".equals(name)) {
            Toast.makeText(this, "请填写收件人!", Toast.LENGTH_SHORT).show();
            return false;
        }
        String phone = addressPhone.getText().toString();
        if (null == phone || "".equals(phone)) {
            Toast.makeText(this, "请填写联系方式!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!Utils.isMobile(phone)) {
            Toast.makeText(this, "请填写正确联系方式!", Toast.LENGTH_SHORT).show();
            return false;
        }
        String zone = mProvince.toString();
        if (null == zone || "".equals(zone)) {
            Toast.makeText(this, "操作失败!", Toast.LENGTH_SHORT).show();
            return false;
        }
        String detail = addressInfo.getText().toString();
        if (null == detail || "".equals(detail)) {
            Toast.makeText(this, "操作失败!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }


    private void updateAddress() {
        UserAddressCurd userAddressCurd = new UserAddressCurd();
        userAddressCurd.setAddressId(addressId);
        userAddressCurd.setCity(mCity);
        userAddressCurd.setConsigneeName(addressName.getText().toString().trim());
        userAddressCurd.setConsigneeTel(addressPhone.getText().toString().trim());
        userAddressCurd.setDistrict(mCounty);
        userAddressCurd.setLocation(addressInfo.getText().toString().trim());
        userAddressCurd.setProvince(mProvince);
        userAddressCurd.setIsDefault(addressDefault.isChecked() ? 1 : 0);
        mPresenter.updateAddress(this, mType, userAddressCurd);
    }

    private void initView() {
        if (bean != null) {
            addressName.setText(bean.getConsigneeName());
            addressName.setSelection(bean.getConsigneeName().length());
            addressPhone.setText(bean.getConsigneeTel());
            address.setText(getString(R.string.address_show, bean.getProvince(), bean.getCity(), bean.getDistrict()));
            addressInfo.setText(bean.getLocation());
            mProvince = bean.getProvince();
            mCity = bean.getCity();
            mCounty = bean.getDistrict();
            addressDefault.setChecked(bean.isDefault());
        }

        if (mType == 0) {
            deleteBtn.setVisibility(View.GONE);
        } else {
            deleteBtn.setVisibility(View.VISIBLE);
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
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAddressTip(bean.getAddressId(), getResources().getString(R.string.del_address_info), -1);
            }
        });
    }

    private void setAddressTip(final String addressId, String message, final int type) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.delAddressList(UpdateAddressActivity.this, addressId);
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();

    }


    private void initActionBar() {
        if (mType == TYPE_ONCREATE) {
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
        if (updateBean.getCode().equals("0")) {
            Toast.makeText(this, "操作成功!", Toast.LENGTH_SHORT).show();
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    EventBus.getDefault().post(new GoAddressActivityEvent());
                    finish();
                }
            }, 500);
        } else {
            Toast.makeText(this, "操作失败!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateAddressError(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void delAddressListSuccess(NetBaseResultBean netShareBean) {
        if (netShareBean.getCode().equals("0")) {
            Toast.makeText(this, "操作成功!", Toast.LENGTH_SHORT).show();
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    EventBus.getDefault().post(new GoAddressActivityEvent());
                    finish();
                }
            }, 500);
        } else {
            Toast.makeText(this, "操作失败!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void delAddressListError(int code, String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
