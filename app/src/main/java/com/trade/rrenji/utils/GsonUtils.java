package com.trade.rrenji.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.trade.rrenji.R;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by jinbangzhu on 12/30/14.
 */
public class GsonUtils {
    private static GsonBuilder sGsonBuilder;
    private static Gson sGson;

    public static <T> T fromJsonWithAlert(Context context, String action, Class<T> tClass) {
        Gson gson = GsonUtils.getGson();
        try {
            return gson.fromJson(action, tClass);
        } catch (Exception e) {
            e.printStackTrace();
            // Parse Json Error
            Toast.makeText(context, R.string.json_parse_error_tip, Toast.LENGTH_LONG).show();
        }
        return null;
    }

    public static <T> T fromJsonWithAlert(Context context, String action, Type typeOfT) {
        Gson gson = GsonUtils.getGson();
        try {
            return gson.fromJson(action, typeOfT);
        } catch (Exception e) {
            e.printStackTrace();
            // Parse Json Error
            Toast.makeText(context, R.string.json_parse_error_tip, Toast.LENGTH_LONG).show();
        }
        return null;
    }

    public static Gson getGson() {
        if (sGson == null) {
            sGson = getGsonBuilder().create();
        }

        return sGson;
    }

    public static GsonBuilder getGsonBuilder() {
        if (sGsonBuilder == null) {
            sGsonBuilder = new GsonBuilder()
                    .registerTypeAdapter(Integer.class, IntegerTypeAdapter)
                    .registerTypeAdapter(int.class, IntegerTypeAdapter)
                    .registerTypeAdapter(Long.class, LongTypeAdapter)
                    .registerTypeAdapter(long.class, LongTypeAdapter)
                    .registerTypeAdapter(Double.class, DoubleTypeAdapter)
                    .registerTypeAdapter(String.class, StringTypeAdapter)
                    .registerTypeAdapter(double.class, DoubleTypeAdapter);
        }

        return sGsonBuilder;
    }

    private static TypeAdapter<String> StringTypeAdapter = new TypeAdapter<String>() {
        @Override
        public void write(JsonWriter out, String value)
                throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.value(value);
        }

        @Override
        public String read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return "";
            }
            try {
                String result = in.nextString();
                if (TextUtils.equals("", result) || result == null) {
                    return "";
                }
                return result;
            } catch (Exception e) {
                throw new JsonSyntaxException(e);
            }
        }
    };

    private static TypeAdapter<Integer> IntegerTypeAdapter = new TypeAdapter<Integer>() {
        @Override
        public void write(JsonWriter out, Integer value)
                throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.value(value);
        }

        @Override
        public Integer read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return 0;
            }
            try {
                String result = in.nextString();
                if (TextUtils.equals("", result)) {
                    return 0;
                }
                return Integer.valueOf(result);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    };

    private static TypeAdapter<Long> LongTypeAdapter = new TypeAdapter<Long>() {
        @Override
        public void write(JsonWriter out, Long value)
                throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.value(value);
        }

        @Override
        public Long read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return 0L;
            }
            try {
                String result = in.nextString();
                if (TextUtils.equals("", result)) {
                    return 0L;
                }
                return Long.valueOf(result);
            } catch (NumberFormatException e) {
                return 0L;
            }
        }
    };

    private static TypeAdapter<Double> DoubleTypeAdapter = new TypeAdapter<Double>() {
        @Override
        public void write(JsonWriter out, Double value)
                throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.value(value);
        }

        @Override
        public Double read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return 0.0;
            }
            try {
                String result = in.nextString();
                if (TextUtils.equals("", result)) {
                    return 0.0;
                }
                return Double.valueOf(result);
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }
    };

}
