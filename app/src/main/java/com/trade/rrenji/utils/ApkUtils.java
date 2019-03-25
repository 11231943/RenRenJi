package com.trade.rrenji.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.util.Log;

/**
 * Provide different APK informations.
 * 
 * @author DongJian
 * @date 2021-12-24
 */
public class ApkUtils {
	/**
	 * This method provide the package's name.
	 * 
	 * @param context
	 * @return String name
	 */
	public static String getPackageName(Context context) {
		PackageManager manager = context.getPackageManager();
		String packageName = "0";
		try {
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			packageName = info.packageName;
		} catch (NameNotFoundException e) {
			Log.e("ApkInfo", e.getMessage());
		}
		return packageName;
	}

	/**
	 * This method provide the package's version name.
	 * 
	 * @param context
	 * @return String name
	 */
	public static String getVersionName(Context context) {
		PackageManager manager = context.getPackageManager();
		String versionName = "0";
		try {
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			versionName = info.versionName;
		} catch (NameNotFoundException e) {
			Log.e("ApkInfo", e.getMessage());
		}
		return versionName;
	}

	/**
	 * This method provide the package's version code
	 * 
	 * @param context
	 * @return int code
	 */
	public static int getVersionCode(Context context) {
		PackageManager manager = context.getPackageManager();
		int versionCode = 0;
		try {
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			versionCode = info.versionCode;
		} catch (NameNotFoundException e) {
			Log.e("ApkInfo", e.getMessage());
		}
		return versionCode;
	}

	public static String getMetaData(Context context, String metaData) {
		PackageManager manager = context.getPackageManager();
		String value = "";
		try {
			ApplicationInfo appInfo = manager.getApplicationInfo(context.getPackageName(),
					PackageManager.GET_META_DATA);
			value = appInfo.metaData.getString(metaData);
		} catch (NameNotFoundException e) {
			Log.e("Get Umeng Channel", e.getMessage());
		}

		return value == null ? "" : value;
	}

	public static boolean useTransition() {
		return VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP;
	}
}
