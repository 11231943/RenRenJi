package com.trade.rrenji.net;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.net.URLEncoder.encode;

public class Md5SecurityUtil {

    /**
     * 签名参数名
     */
    public final static String OAUTH_SIGNATURE = "sign";
    /**
     * 时间戳参数名
     */
    public final static String OAUTH_TIMESTAMP = "timestamp";

    /**
     * 生成md5 签名
     *
     * @param nameValuePairs
     * @return
     * @throws Exception
     */
    public static String getSignatureValue(List<NameValuePair> nameValuePairs) throws Exception {

        Map<String, String> parameterMap = new HashMap<String, String>();
        for (NameValuePair pair : nameValuePairs) {
            parameterMap.put(pair.name, pair.value);
        }

        ArrayList<NameValuePair> currentPairs = new ArrayList<NameValuePair>(nameValuePairs);

        String timestamp = System.currentTimeMillis() + "";

        if (!parameterMap.containsKey(OAUTH_TIMESTAMP)) {
            currentPairs.add(new NameValuePair(OAUTH_TIMESTAMP, timestamp));
        }
        String signatureBaseString = getSignatureBaseString(nameValuePairs);
        String signatureValue = getSignatureValue(signatureBaseString);

        return signatureValue;
    }

    /**
     * 组装请求的url(多用于测试)
     *
     * @param nameValuePairs 除oauth_signature 外的参数值对集合
     * @return
     * @throws Exception
     */
    public static String buildUrl(List<NameValuePair> nameValuePairs) throws Exception {

        //将除oauth_signature 外的参数集合按照字母升序排列
        Collections.sort(nameValuePairs, new Comparator<NameValuePair>() {
            @Override
            public int compare(NameValuePair o1, NameValuePair o2) {
                return o1.name.compareTo(o2.name);
            }
        });

        System.out.println("#pairs_sort {}:" + nameValuePairs);

        StringBuilder sb = new StringBuilder(40);
        for (int i = 0; i < nameValuePairs.size(); i++) {
            NameValuePair pair = nameValuePairs.get(i);
            if (i > 0)
                sb.append("&");
            sb.append(pair.name).append("=").append(pair.value);
        }
        String signatureBaseString = sb.toString();
        System.out.println("#buildurl: {}" + signatureBaseString);
        return signatureBaseString;
    }

    /**
     * 获取签名
     * 获取签名基础串 -->> urlencode(a=x&b=y&...)
     *
     * @param nameValuePairs 除oauth_signature 外的参数值对集合
     * @return
     * @throws Exception
     */
    public static String getSignatureBaseString(List<NameValuePair> nameValuePairs) throws Exception {

        //将除oauth_signature 外的参数集合按照字母升序排列
        Collections.sort(nameValuePairs, new Comparator<NameValuePair>() {
            @Override
            public int compare(NameValuePair o1, NameValuePair o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        System.out.println("#pairs_sort {}" + nameValuePairs);

        StringBuilder sb = new StringBuilder(40);
        for (int i = 0; i < nameValuePairs.size(); i++) {
            NameValuePair pair = nameValuePairs.get(i);
            if (i > 0)
                sb.append("%26");
            sb.append(encode(pair.name, "utf-8")).append("%3D").append(encode(pair.value, "utf-8"));
        }
        String signatureBaseString = sb.toString();

        System.out.println("#signatureBaseString: {}" + signatureBaseString);
        return signatureBaseString;
    }

    /**
     * @param signatureBaseString 签名基础串
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private static String getSignatureValue(String signatureBaseString)
            throws NoSuchAlgorithmException, InvalidKeyException {

        String signatureValue = MD5Tool.GetMD5Code(signatureBaseString, true);

        System.out.println("signatureBaseString:{}" + signatureBaseString + ",signatureValue:{}" + signatureValue);
        return signatureValue;
    }



}