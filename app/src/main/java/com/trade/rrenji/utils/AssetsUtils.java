package com.trade.rrenji.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetsUtils {

    /**
     * read file content
     *
     * @param context   the context
     * @param assetPath the asset path
     * @return String string
     */
    public static String readText(Context context, String assetPath) {
        Log.d("AssetsUtils","read assets file as text: " + assetPath);
        try {
            StringBuilder sb = new StringBuilder();
            InputStream is = context.getAssets().open(assetPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is,
                    "utf-8"));
            while (br.ready()) {
                String line = br.readLine();
                if (line != null) {
                    // 读出来文件末尾多了“null”?
                    sb.append(line).append("\n");
                }
            }
            br.close();
            is.close();
            return sb.toString();
        } catch (Exception e) {
            Log.e("AssetsUtils","read assets file failed", e);
            return "";
        }
    }

}
