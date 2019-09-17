package com.trade.rrenji.biz.account.ui.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moonster.imagepicker.ImagePicker;
import com.moonster.imagepicker.data.ImageBean;
import com.moonster.imagepicker.data.ImagePickType;
import com.moonster.imagepicker.utils.GlideImagePickerDisplayer;
import com.trade.rrenji.MiGoApplication;
import com.trade.rrenji.R;
import com.trade.rrenji.bean.upload.NetUploadBean;
import com.trade.rrenji.biz.account.presenter.UpdateUserInfoActivityPresenter;
import com.trade.rrenji.biz.account.presenter.UpdateUsrInfoActivityPresenterImpl;
import com.trade.rrenji.biz.account.ui.view.UpdateUserInfoActivityView;
import com.trade.rrenji.biz.address.picker.AddressPicker;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.base.NetBaseResultBean;
import com.trade.rrenji.biz.upload.model.UploadModel;
import com.trade.rrenji.biz.upload.model.UploadModelImpl;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.AssetsUtils;
import com.trade.rrenji.utils.CollectionUtils;
import com.trade.rrenji.utils.Contetns;
import com.trade.rrenji.utils.GlideUtils;
import com.trade.rrenji.utils.GsonUtils;
import com.trade.rrenji.utils.SettingUtils;
import com.trade.rrenji.utils.ThreadPoolManager;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import com.trade.rrenji.biz.photo.ImageEditActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

public class AccountActivity extends BaseActivity implements UpdateUserInfoActivityView {
    public static final int CROP_IMAGE = 7;
    public static final int CROP_OPTIONS_SQUARE_ONLY = 1;
    @Bind(R.id.user_avatar)
    ImageView userAvatar;
    @Bind(R.id.user_avatar_go)
    ImageView userAvatarGo;
    @Bind(R.id.user_avatar_layout)
    RelativeLayout userAvatarLayout;
    @Bind(R.id.user_name)
    EditText userName;
    @Bind(R.id.user_name_go)
    ImageView userNameGo;
    @Bind(R.id.user_name_layout)
    RelativeLayout userNameLayout;
    @Bind(R.id.user_phone)
    TextView userPhone;
    @Bind(R.id.user_phone_go)
    ImageView userPhoneGo;
    @Bind(R.id.user_phone_layout)
    RelativeLayout userPhoneLayout;
    @Bind(R.id.text_sex)
    TextView textSex;
    @Bind(R.id.icon_sex_go)
    ImageView iconSexGo;
    @Bind(R.id.bind_sex)
    RelativeLayout bindSex;
    @Bind(R.id.text_address)
    TextView textAddress;
    @Bind(R.id.text_address_go)
    ImageView textAddressGo;
    @Bind(R.id.address_layout)
    RelativeLayout addressLayout;
    private int cropOptions = CROP_OPTIONS_SQUARE_ONLY;

    UpdateUserInfoActivityPresenter mPresenter;
    private AddressPicker mAddressPicker;
    private ArrayList<AddressPicker.Province> mData;
    private Handler mHandler = new Handler();
    private String[] item = {"男", "女"};
    private String mSex = "0";
    private String mProvince;
    private String mCity;
    private String mCounty;
    public String avatarPath = "";


    private static int REQUEST_CODE = 10001;
    private String mPathStr = "";
    UploadModel mUploadModel;

    AlertDialog.Builder mBuilder;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_main_layout);
        ButterKnife.bind(this);
        setActionBarTitle("资料编辑");
        mUploadModel = new UploadModelImpl(this);
        initUser();
    }

    private void pick() {
        new ImagePicker.Builder()
                .pickType(ImagePickType.MUTIL)//设置选取类型(拍照、单选、多选)
                .maxNum(1)//设置最大选择数量(拍照和单选都是1，修改后也无效)
                .needCamera(true)//是否需要在界面中显示相机入口(类似微信)
                .cachePath(MiGoApplication.CACHE_PATH)//自定义缓存路径
                .doCrop(1, 1, 300, 300)//裁剪功能需要调用这个方法，多选模式下无效
                .displayer(new GlideImagePickerDisplayer())//自定义图片加载器，默认是Glide实现的,可自定义图片加载器
                .build()
                .start(this, REQUEST_CODE, REQUEST_CODE);

    }


    private void initUser() {
        textSex.setText(SettingUtils.getInstance().getUserSex().equals("0") ? "男" : "女");
        userAvatarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick();
            }
        });
        textAddress.setText(SettingUtils.getInstance().getUserAddress());
        userName.setText(SettingUtils.getInstance().getUsername());

        if (TextUtils.isEmpty(SettingUtils.getInstance().getUserImg())) {
            GlideUtils.getInstance().loadIcon(this, R.drawable.user_default_icon, R.drawable.user_default_icon, userAvatar);

        } else {
            GlideUtils.getInstance().loadCircleIcon(this, SettingUtils.getInstance().getUserImg(), R.drawable.user_default_icon, userAvatar);
        }
        bindSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        addressLayout.setOnClickListener(new View.OnClickListener() {
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
                        textAddress.setText(getString(R.string.account_address_show, province, city));
                    }
                });
        ThreadPoolManager.getInstance().addTask(new MyRunnable());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_update, menu);
        return true;
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择");
        builder.setItems(item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mSex = which + "";
                textSex.setText(item[which]);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_save_btn:
                new AlertDialog.Builder(this)
                        .setMessage("是否保存修改的资料？")
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                update();
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void update() {
        if (TextUtils.isEmpty(mPathStr)) {
            mPathStr = SettingUtils.getInstance().getUserImg();
        }
        String mUserName = userName.getText().toString().trim();

        if (null == mUserName || "".equals(mUserName)) {
            Toast.makeText(this, "请填写昵称!", Toast.LENGTH_SHORT).show();
            return;
        }
        String sex = textSex.getText().toString().trim();
        String mAddress = textAddress.getText().toString().trim();
        if (null == mAddress || "".equals(mAddress)) {
            Toast.makeText(this, "请填写地址!", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.updateUserInfo(this, mPathStr, mUserName, mSex, mAddress);
    }

    @Override
    protected void attachPresenter() {
        mPresenter = new UpdateUsrInfoActivityPresenterImpl(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mPresenter.detachView();
        mPresenter = null;
    }

    @Override
    public void updateUserInfoSuccess(NetBaseResultBean netBaseResultBean) {
        if (netBaseResultBean.getCode().equals(Contetns.RESPONSE_OK)) {
            SettingUtils.getInstance().setUserImg(mPathStr);
            Toast.makeText(this, "修改资料成功！", Toast.LENGTH_SHORT).show();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 500);
        } else {
            Toast.makeText(this, "修改资料失败！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateUserInfoError(int code, String msg) {
        Toast.makeText(this, "修改资料失败！", Toast.LENGTH_SHORT).show();
    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            String json = AssetsUtils.readText(AccountActivity.this, "city.json");
            mData = GsonUtils.getGson().fromJson(json,
                    new TypeToken<ArrayList<AddressPicker.Province>>() {
                    }.getType());

            if (!CollectionUtils.isEmpty(mData)) {
                mAddressPicker.init(mData);
            }
        }
    }

    //重写Activity或Fragment中OnActivityResult()
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CROP_IMAGE) {
            if (data != null && data.getStringExtra("dest_file") != null) {
                avatarPath = data.getStringExtra("dest_file");
                GlideUtils.getInstance().loadCircleIcon(this, avatarPath, R.drawable.user_default_icon, userAvatar);
                showUploadDialog();
                mUploadModel.upload(this, mPathStr, new XUtils.ResultListener() {
                    @Override
                    public void onResponse(String result) {
                        try {
                            if (mProgressDialog != null) {
                                mProgressDialog.dismiss();
                            }
                            Gson gson = new Gson();
                            NetUploadBean uploadBean = gson.fromJson(result, NetUploadBean.class);
                            if (uploadBean.getCode().equals(Contetns.RESPONSE_OK)) {
                                mPathStr = uploadBean.getResult().getUrl();
                                Toast.makeText(AccountActivity.this, "上传图片成功！", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(AccountActivity.this, "上传失败！", Toast.LENGTH_SHORT).show();
                            if (mProgressDialog != null) {
                                mProgressDialog.dismiss();
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable error) {
                        Toast.makeText(AccountActivity.this, "上传失败！", Toast.LENGTH_SHORT).show();
                        if (mProgressDialog != null) {
                            mProgressDialog.dismiss();
                        }
                    }
                });
            }
        } else if (requestCode == REQUEST_CODE && resultCode == REQUEST_CODE && data != null) {
            //获取选择的图片数据
            List<ImageBean> resultList = data.getParcelableArrayListExtra(ImagePicker.INTENT_RESULT_DATA);
            mPathStr = resultList.get(0).getImagePath();
            cropPhoto(mPathStr);
//            GlideUtils.getInstance().loadCircleIcon(this, mPathStr, R.drawable.user_default_icon, user_avatar);
//
        }
    }

    /**
     * 剪切图片方法
     */
    public void cropPhoto(String srcPhotoPath) {
        File editedPhoto;
        try {
            editedPhoto = createTempFile(System.currentTimeMillis() + "edited.jpg");
            Log.d("editedPhoto", editedPhoto.getAbsolutePath());
        } catch (IOException e) {
            Toast.makeText(this, R.string.error_create_temp_file, Toast.LENGTH_LONG).show();
            Log.e("Failed to edit a photo!", e.getMessage());
            return;
        }

//        Intent intent = new Intent(this, ImageEditActivity.class);
//        intent.putExtra(ImageEditActivity.SRC_FILE_KEY, srcPhotoPath);
//        intent.putExtra(ImageEditActivity.DEST_FILE_KEY, editedPhoto.getAbsolutePath());
//        intent.putExtra(ImageEditActivity.CROP_OPTION_KEY, cropOptions);
//        startActivityForResult(intent, CROP_IMAGE);
    }

    private File createTempFile(String name) throws IOException {
        File storageDir = getFilesDir();
        File photo = new File(storageDir, name);
        if (photo.exists()) {
            photo.delete();
        }
        photo.createNewFile();
        return photo;
    }

    private void showUploadDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("上传图像");
        mProgressDialog.setMessage("上传中...");
        mProgressDialog.setIndeterminate(true);// 是否形成一个加载动画  true表示不明确加载进度形成转圈动画  false 表示明确加载进度
        mProgressDialog.setCancelable(false);//点击返回键或者dialog四周是否关闭dialog  true表示可以关闭 false表示不可关闭
        mProgressDialog.show();
    }
}
