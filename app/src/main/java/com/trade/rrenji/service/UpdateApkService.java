package com.trade.rrenji.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;


import com.trade.rrenji.MainActivity;
import com.trade.rrenji.R;
import com.trade.rrenji.utils.FileDownloader;

import java.io.File;


public class UpdateApkService extends Service {
    //通知栏
    private NotificationManager updateNotificationManager = null;
    private NotificationCompat.Builder mBuilder;
    //通知栏跳转Intent
    private Intent updateIntent = null;

    //下载相关信息
    private String apkName;
    private String apkVersion;
    private String downloadURL;
    private int updateType;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null)
            updateIntent = intent;

        if (updateIntent == null) {
            return super.onStartCommand(intent, flags, startId);
        }

        //获取传值
        apkName = updateIntent.getStringExtra("apkName");
        apkVersion = updateIntent.getStringExtra("apkVersion");
        updateType = updateIntent.getIntExtra("updateType", 0);
        downloadURL = updateIntent.getStringExtra("downloadURL");

        final FileDownloader fileDownloader = new FileDownloader(downloadURL, apkName);
        fileDownloader.setOnDownloadListener(new FileDownloader.OnDownloadListener() {
            @Override
            public void onDownloading(int progress) {
                String title = getString(R.string.downloading_now)
                        + fileDownloader.getSaveFile().getName();
                updateNotificationManager.notify(0,
                        mBuilder.setContentTitle(title)
                                .setContentText(progress + "%")
                                .build());
            }

            @Override
            public void onDownloaded(File saveFile) {
                //点击安装PendingIntent
                Uri uri = Uri.fromFile(saveFile);
                Intent installIntent = new Intent(Intent.ACTION_VIEW);
                installIntent.setDataAndType(uri, "application/vnd.android.package-archive");
                installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                PendingIntent updatePendingIntent = PendingIntent.getActivity(UpdateApkService.this, 0,
                        installIntent, 0);
                mBuilder.setSmallIcon(android.R.drawable.stat_sys_download_done)
                        .setDefaults(Notification.DEFAULT_SOUND)
                        .setContentTitle(saveFile.getName())
                        .setContentText(getString(R.string.done_download))
                        .setContentIntent(updatePendingIntent);
                updateNotificationManager.notify(0, mBuilder.build());

                if (updateType == MainActivity.UPDATE_STRATEGY_EASY) {
                    startActivity(installIntent);
                }

                //停止服务
                stopSelf();
            }

            @Override
            public void onFailed(File saveFile) {
                //下载失败
                updateNotificationManager.notify(0,
                        mBuilder.setContentTitle(saveFile.getName())
                                .setContentText(
                                        getString(R.string.upgrade_dialog_download_failed))
                                .build());
                //停止服务
                stopSelf();
            }
        });


        this.updateNotificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        PendingIntent updatePendingIntent = PendingIntent.getActivity(this, 0, new Intent(), 0);
        mBuilder = new NotificationCompat.Builder(UpdateApkService.this);

        //设置通知栏显示内容
        mBuilder.setSmallIcon(android.R.drawable.stat_sys_download)
                .setTicker(getString(R.string.start_download))
                .setContentIntent(updatePendingIntent);

        //发出通知
        updateNotificationManager.notify(0, mBuilder.setContentTitle(apkName)
                                                    .setContentText("0%")
                                                    .build());

        //开启一个新的线程下载，如果使用Service同步下载，会导致ANR问题，Service本身也会阻塞
        fileDownloader.startDownload();

        return super.onStartCommand(intent, flags, startId);
    }

}
