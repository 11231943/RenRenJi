package com.trade.rrenji.utils;

/**
 * Created by monster on 8/4/18.
 */

public class Contetns {
    public static final int STATE_OK = 0;
    public static final String RESPONSE_OK = "0";
    public static final int TOKEN_ERROR = 103;
    public static final int JSON_PART = 10000;
    public static final int NET_ERROR = 10001;
    public static String DEVICE_ID = "mDeviceId";

    public static String sessionKey = "a7c03182a76047fdad811aa9f45715dc";

    private static int DEFAULT = 10;//每页数据

    public static boolean hasMoreData(int size) {
        return DEFAULT <= size ? true : false;
    }

}
