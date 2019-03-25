package com.trade.rrenji.utils;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloader {

    //下载状态
    public final static int DOWNLOAD_COMPLETE = 0;
    public final static int DOWNLOAD_FAIL = 1;
    public final static int DOWNLOADING = 2;

    //文件存储
    private File updateDir = null;
    private File updateFile = null;

    private String downloadURL;

    private OnDownloadListener onDownloadListener;

    public void setOnDownloadListener(OnDownloadListener l){
        this.onDownloadListener = l;
    }

    public FileDownloader(String downloadURL, String apkName){
        this.downloadURL = downloadURL;
        //创建文件
        if (TextUtils.equals(Environment.MEDIA_MOUNTED,
                Environment.getExternalStorageState())) {
            updateDir = new File(Environment.getExternalStorageDirectory(),
                    Environment.DIRECTORY_DOWNLOADS);
            updateFile = new File(updateDir.getPath(), apkName);
        }
    }

    public void startDownload(){
        new Thread(new updateRunnable()).start();
    }


    public long downloadUpdateFile(String downloadUrl, File saveFile) throws Exception {
        int downloadCount = 0;
        int currentSize = 0;
        long totalSize = 0;
        int updateTotalSize = 0;

        HttpURLConnection httpConnection = null;
        InputStream is = null;
        FileOutputStream fos = null;

        try {
            URL url = new URL(downloadUrl);
            httpConnection = (HttpURLConnection)url.openConnection();
            //httpConnection.setRequestProperty("User-Agent", "PacificHttpClient");
            if(currentSize > 0) {
                httpConnection.setRequestProperty("RANGE", "bytes=" + currentSize + "-");
            }
            httpConnection.setConnectTimeout(10000);
            httpConnection.setReadTimeout(20000);
            updateTotalSize = httpConnection.getContentLength();
            if (httpConnection.getResponseCode() == 404) {
                throw new Exception("fail!");
            }
            is = httpConnection.getInputStream();
            fos = new FileOutputStream(saveFile, false);
            byte buffer[] = new byte[4096];
            int readsize = 0;
            while((readsize = is.read(buffer)) > 0){
                fos.write(buffer, 0, readsize);
                totalSize += readsize;
                //为了防止频繁的通知导致应用吃紧，百分比增加5才通知一次
                if((downloadCount == 0)||(int) (totalSize*100/updateTotalSize)-5>downloadCount){
                    downloadCount += 5;
                    //onDownloadListener.onDownloading((int) totalSize * 100 / updateTotalSize);
                    Message message = updateHandler.obtainMessage();
                    message.what = DOWNLOADING;
                    message.arg1 = (int) totalSize * 100 / updateTotalSize;
                    updateHandler.sendMessage(message);
                }
            }
        } finally {
            if(httpConnection != null) {
                httpConnection.disconnect();
            }
            if(is != null) {
                is.close();
            }
            if(fos != null) {
                fos.close();
            }
        }
        return totalSize;
    }

    public class updateRunnable implements Runnable {
        Message message = updateHandler.obtainMessage();
        public void run() {
            message.what = DOWNLOAD_COMPLETE;
            try{
                //增加权限;
                if(!updateDir.exists()){
                    updateDir.mkdirs();
                }
                if(!updateFile.exists()){
                    updateFile.createNewFile();
                }
                //下载函数，以QQ为例子
                //增加权限;
                long downloadSize = downloadUpdateFile(downloadURL,updateFile);
                if(downloadSize>0){
                    //下载成功
                    updateHandler.sendMessage(message);
                }
            }catch(Exception ex){
                ex.printStackTrace();
                message.what = DOWNLOAD_FAIL;
                //下载失败
                updateHandler.sendMessage(message);
            }
        }
    }

    private Handler updateHandler = new  Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case FileDownloader.DOWNLOAD_COMPLETE:
                    onDownloadListener.onDownloaded(updateFile);
                    break;
                case FileDownloader.DOWNLOAD_FAIL:
                    onDownloadListener.onFailed(updateFile);
                    break;
                case FileDownloader.DOWNLOADING:
                    onDownloadListener.onDownloading(msg.arg1);
            }
        }
    };

    public File getSaveFile(){
        return updateFile;
    }

    public interface OnDownloadListener{
        void onDownloading(int progress);
        void onDownloaded(File saveFile);
        void onFailed(File saveFile);
    }

}
