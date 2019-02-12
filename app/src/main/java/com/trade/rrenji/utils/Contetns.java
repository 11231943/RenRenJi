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

//    public static String sessionKey = "90574927cfe448a4924496d9d1a38c1b";
    public static String sessionKey = "087af885620d413e8639621f405321db";

    //http://112.124.98.145:8080/api/rs/address/list/v1/90574927cfe448a4924496d9d1a38c1b/-9223372035305833421/1/test

    private static int DEFAULT = 10;//每页数据

    public static boolean hasMoreData(int size) {
        return DEFAULT <= size ? true : false;
    }

}
