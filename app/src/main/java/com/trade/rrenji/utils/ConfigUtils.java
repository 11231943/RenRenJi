package com.trade.rrenji.utils;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessControlException;
import java.util.Properties;

public class ConfigUtils {
	private byte[] propertiesMonitor = new byte[0];
	private Properties defaultProperty;

	private static ConfigUtils config;
	
	public synchronized static ConfigUtils getInstance(){
		if(config == null){
			config = new ConfigUtils();
		}
		return config;
	}
	
	private ConfigUtils(){
		defaultProperty = new Properties();
	}

	public void init(Context context, int configRes) {
		ThreadPoolManager.getInstance().addTask(new LoadConfigRunnable(context, configRes));
	}
	
	private ConfigUtils loadProperties(InputStream is){
		try {
			synchronized(propertiesMonitor) {
				config.defaultProperty.load(is);
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return config;
	}

	private class LoadConfigRunnable implements Runnable {
		private Context mContext;
		private int mConfigRawRes;

		public LoadConfigRunnable(Context context, int configRes) {
			mContext = context;
			mConfigRawRes = configRes;
		}

		@Override
		public void run() {
			InputStream pIns = mContext.getResources().openRawResource(mConfigRawRes);
			loadProperties(pIns);
		}
	}

	/*
	 * 常用方法
	 */
	public boolean getBoolean(String name) {
		String value = getProperty(name);
		return Boolean.parseBoolean(value);
	}

	public int getIntProperty(String name) {
		String value = getProperty(name);
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException nfe) {
			return -1;
		}
	}

	public int getIntProperty(String name, int fallbackValue) {
		String value = getProperty(name, String.valueOf(fallbackValue));
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException nfe) {
			return -1;
		}
	}

	public long getLongProperty(String name) {
		String value = getProperty(name);
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException nfe) {
			return -1;
		}
	}

	public String getProperty(String name) {
		return getProperty(name, null);
	}

	public String getProperty(String name, String fallbackValue) {
		String value;
		try {
			value = System.getProperty(name, fallbackValue);
			if (null == value) {
				value = config.defaultProperty.getProperty(name);
			}
			if (null == value) {
				String fallback = config.defaultProperty.getProperty(name
						+ ".fallback");
				if (null != fallback) {
					value = System.getProperty(fallback);
				}
			}
		} catch (AccessControlException ace) {
			value = fallbackValue;
		}
		return value;
	}

}
