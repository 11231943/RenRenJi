package com.trade.rrenji;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.trade.rrenji.bean.check.NetCheckBean;
import com.trade.rrenji.biz.base.BaseActivity;
import com.trade.rrenji.biz.version.presenter.CheckPresenter;
import com.trade.rrenji.biz.version.presenter.CheckPresenterImpl;
import com.trade.rrenji.biz.version.ui.view.CheckActivityView;
import com.trade.rrenji.fragment.HomeTabFragment;
import com.trade.rrenji.fragment.CategoryTabFragment;
import com.trade.rrenji.fragment.TechTabFragment;
import com.trade.rrenji.fragment.DryingTabFragment;
import com.trade.rrenji.fragment.MineFragment;
import com.trade.rrenji.service.UpdateApkService;
import com.trade.rrenji.utils.FileDownloader;
import com.trade.rrenji.utils.FragmentTabHost;
import com.trade.rrenji.utils.TabLayout;
import com.trade.rrenji.utils.ViewUtils;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements CheckActivityView {

    //更新策略：默认替用户下载更新，强制用户安装
    public static final int UPDATE_STRATEGY_STRICT = 3;
    //更新策略：默认替用户下载更新，提示用户进行安装
    public static final int UPDATE_STRATEGY_EASY = 1;
    //更新策略：默认替用户下载更新，但是不提示用户是否安装
    public static final int UPDATE_STRATEGY_OPTIONAL = 2;

    private long mExitTime;


    public String[] tabTags = new String[]{"nearby", "live", "rainbow", "message", "mine"};
    public Class[] tabCls = new Class[]{HomeTabFragment.class, CategoryTabFragment.class,
            TechTabFragment.class, DryingTabFragment.class, MineFragment.class};
    int[] tabCustomView = new int[]{R.layout.main_tab_near, R.layout.main_tab_live,
            R.layout.main_tab_discovered, R.layout.main_tab_drying, R.layout.main_tab_mime};

    @ViewInject(android.R.id.tabhost)
    public FragmentTabHost mTabHost;
    @ViewInject(R.id.main_tabs)
    public TabLayout mTabLayout;
    @ViewInject(R.id.msgCount)
    public TextView msgCount;
    @ViewInject(R.id.msgCountLayout)
    public View msgCountLayout;
    private int mCurrentItem;

    CheckPresenter mCheckPresenter;

    String[] permissions = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy( builder.build() );
        }
//        mCheckPresenter.getCheck(this);
        initEvent();
        addPermission();

    }
    private void addPermission() {
        AndPermission.with(this)
                .runtime()
                .permission(permissions)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {

                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Toast.makeText(MainActivity.this, "沒有权限，请到设置页面授予权限.",Toast.LENGTH_SHORT).show();
                    }
                })
                .start();
    }

    protected void initEvent() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.container, false);
        for (int i = 0; i < tabTags.length; i++) {
            mTabHost.addTab(mTabHost.newTabSpec(tabTags[i]).setIndicator(tabTags[i]),
                    tabCls[i], null);
            mTabLayout.addTab(mTabLayout.newTab().setTag(tabTags[i]));
            mTabLayout.getTabAt(i).setCustomView(tabCustomView[i]);
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mCurrentItem = tab.getPosition();
                mTabHost.setCurrentTabByTag((String) tab.getTag());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
//        locateMsgCount();
//        updateUnreadNumView(12);
    }

    /**
     * 未读数标签的位置
     */
    private void locateMsgCount() {
        final int tabCount = 5;     // 总tab的数量
        final int prevTabCount = 3; // 消息tab之前的tab的数量
        int leftPadding = (int) (ViewUtils.getScreenWidth(this)
                * (1 + prevTabCount * 2) / (tabCount * 2)
                + getResources().getDimension(R.dimen.indicate_text_offset));
        msgCountLayout.setPadding(leftPadding, 0, 0, 0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 更新消息数
     *
     * @param unreadMsgNum
     */
    public void updateUnreadNumView(final int unreadMsgNum) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (unreadMsgNum <= 0) {
                    msgCount.setVisibility(View.GONE);
                    msgCount.setText("");
                } else if (unreadMsgNum <= 999) {
                    msgCount.setVisibility(View.VISIBLE);
                    msgCount.setText(String.valueOf(unreadMsgNum));
                } else {
                    msgCount.setVisibility(View.VISIBLE);
                    msgCount.setText("999+");
                }
            }
        });

    }

    @Override
    protected void attachPresenter() {
        mCheckPresenter = new CheckPresenterImpl(this);
        mCheckPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mCheckPresenter.detachView();
        mCheckPresenter = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getCheckSuccess(NetCheckBean netShareBean) {
        final NetCheckBean.ResultBean resultBean = netShareBean.getResult();
        resultBean.setApkUrl("https://d.douyucdn.cn/wsd-pkg-div/2019/03/25/c27cc261e0cac8daea9e9636650b5e2d_market_5.6.0_20190325125959.apk");
        if (resultBean.isNeedUpdate()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle(R.string.update_title)
                    .setMessage(getString(R.string.update_info, "3.0",
                            resultBean.getMsg()));
            if (resultBean.isForce()) {
                builder.setCancelable(false);
            } else {
                builder.setNegativeButton(R.string.cancel, null);
            }
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (resultBean.isForce()) {
                        downloadForeground("RenRenJi" + "_v.3.0", resultBean.getApkUrl());
                    } else {
                        downloadBackground("RenRenJi" + "_v.3.0", resultBean.getApkUrl(), resultBean.isForce() ? UPDATE_STRATEGY_STRICT : UPDATE_STRATEGY_EASY);
                    }
                }
            });
            builder.show();
        }
    }

    private void downloadBackground(String apkName, String downloadURL, int updateType) {
        Intent updateIntent = new Intent(this,
                UpdateApkService.class);
        updateIntent.putExtra("apkName", apkName);
        updateIntent.putExtra("apkVersion", "3.0");
        updateIntent.putExtra("downloadURL", downloadURL);
        updateIntent.putExtra("updateType", updateType);
        startService(updateIntent);
    }


    private void downloadForeground(String apkName, String downloadURL) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage(getString(R.string.start_download));
        pd.show();
        final FileDownloader fileDownloader = new FileDownloader(downloadURL, apkName);
        fileDownloader.setOnDownloadListener(
                new FileDownloader.OnDownloadListener() {
                    @Override
                    public void onDownloading(int progress) {
                        pd.setMessage(
                                getString(R.string.upgrade_dialog_download, progress));
                    }

                    @Override
                    public void onDownloaded(File saveFile) {
                        pd.dismiss();
                        // 安装apk
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(
                                fileDownloader.getSaveFile()),
                                "application/vnd.android.package-archive");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailed(File saveFile) {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this, getString(
                                R.string.upgrade_dialog_download_failed),
                                Toast.LENGTH_SHORT).show();
                    }
                });
        fileDownloader.startDownload();
    }


    @Override
    public void getCheckError(int code, String msg) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 询问是否退出app
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 4000) {
                Toast.makeText(this,
                        getResources().getString(R.string.once_again_to_exit),
                        Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                MiGoApplication.getApp().exit(false,true);
                finish();
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


}
