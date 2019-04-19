package com.trade.rrenji.biz.home.model;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import com.trade.rrenji.R;
import com.trade.rrenji.net.ServiceHelper;
import com.trade.rrenji.net.XUtils;
import com.trade.rrenji.utils.Contetns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by monster on 8/4/18.
 */

public class HomeModelImpl implements HomeModel {

    private String json ="{\n" +
            "  \"code\": \"0\",\n" +
            "  \"msg\": \"SUCCESS\",\n" +
            "  \"result\": {\n" +
            "    \"banner\": [\n" +
            "      {\n" +
            "        \"adImg\": \"http://qiniu.rrenji.com/FrqCCd8XKMlK9Jc4nUqcPRxtRteK\",\n" +
            "        \"adUrl\": \"https://baike.baidu.com/item/%E6%B7%B1%E5%9C%B3%E4%BA%BA%E4%BA%BA%E6%9C%BA%E7%A7%91%E6%8A%80%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8/19700805\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"adImg\": \"http://qiniu.rrenji.com/FiLRibRrGwz4Vp1RSnKtpMjKVFpW\",\n" +
            "        \"adUrl\": \"https://baike.baidu.com/item/%E6%B7%B1%E5%9C%B3%E4%BA%BA%E4%BA%BA%E6%9C%BA%E7%A7%91%E6%8A%80%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8/19700805\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"adImg\": \"http://qiniu.rrenji.com/FvkKkE-iD1OxU43vRD_Vko0YLHJA\",\n" +
            "        \"adUrl\": \"https://baike.baidu.com/item/%E6%B7%B1%E5%9C%B3%E4%BA%BA%E4%BA%BA%E6%9C%BA%E7%A7%91%E6%8A%80%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8/19700805\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"categoryList\": [\n" +
            "      {\n" +
            "        \"categoryId\": 1,\n" +
            "        \"categoryImg\": \"http://qiniu.rrenji.com/FvkKkE-iD1OxU43vRD_Vko0YLHJA\",\n" +
            "        \"categoryName\": \"iPhone\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"categoryId\": 2,\n" +
            "        \"categoryImg\": \"http://qiniu.rrenji.com/FvkKkE-iD1OxU43vRD_Vko0YLHJA\",\n" +
            "        \"categoryName\": \"安卓\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"categoryId\": 3,\n" +
            "        \"categoryImg\": \"http://qiniu.rrenji.com/FvkKkE-iD1OxU43vRD_Vko0YLHJA\",\n" +
            "        \"categoryName\": \"笔记本\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"categoryId\": 4,\n" +
            "        \"categoryImg\": \"http://qiniu.rrenji.com/FvkKkE-iD1OxU43vRD_Vko0YLHJA\",\n" +
            "        \"categoryName\": \"配件\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"hotData\": [\n" +
            "      {\n" +
            "        \"hotImg\": \"http://qiniu.rrenji.com/FvkKkE-iD1OxU43vRD_Vko0YLHJA\",\n" +
            "        \"hotContent\": \"平板性价比王者iPad mini2，仅售1099。\",\n" +
            "        \"hotDesc\": \"活动限量20台，手快有，手慢无。【支持支付宝花呗和京东白条】\",\n" +
            "        \"productId\": \"10001\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"hotImg\": \"http://qiniu.rrenji.com/FiLRibRrGwz4Vp1RSnKtpMjKVFpW\",\n" +
            "        \"hotContent\": \"平板性价比王者iPad mini2，仅售1099。\",\n" +
            "        \"hotDesc\": \"活动限量20台，手快有，手慢无。【支持支付宝花呗和京东白条】\",\n" +
            "        \"productId\": \"10001\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"hotIphone\": [\n" +
            "      {\n" +
            "        \"goodsId\": \"366\",\n" +
            "        \"goodsCode\": \"1540026007096\",\n" +
            "        \"discoverImg\": \"http://qiniu.rrenji.com/Fvo-obQ9iXsh9lRWO31ZNH9YmivW\",\n" +
            "        \"goodsPrice\": 3799,\n" +
            "        \"originalPrice\": 3888,\n" +
            "        \"goodsName\": \"全新 华为P20 6+128G 全色\",\n" +
            "        \"subtitleList\": [],\n" +
            "        \"conditionId\": 1,\n" +
            "        \"version\": \"\",\n" +
            "        \"memory\": \"128G\",\n" +
            "        \"color\": \"黑色\",\n" +
            "        \"newLog\": 3\n" +
            "      },\n" +
            "      {\n" +
            "        \"goodsId\": \"379\",\n" +
            "        \"goodsCode\": \"1536723211135\",\n" +
            "        \"discoverImg\": \"http://qiniu.rrenji.com/FoV35NIHQH2VfeVBdnkhEuxJjjp3\",\n" +
            "        \"goodsPrice\": 2220,\n" +
            "        \"originalPrice\": 2488,\n" +
            "        \"goodsName\": \"全新 华为平板M5 10寸32G\",\n" +
            "        \"subtitleList\": [],\n" +
            "        \"conditionId\": 1,\n" +
            "        \"version\": \"国行\",\n" +
            "        \"memory\": \"32G\",\n" +
            "        \"color\": \"金色\",\n" +
            "        \"newLog\": 2\n" +
            "      },\n" +
            "      {\n" +
            "        \"goodsId\": \"366\",\n" +
            "        \"goodsCode\": \"1540026007096\",\n" +
            "        \"discoverImg\": \"http://qiniu.rrenji.com/Fvo-obQ9iXsh9lRWO31ZNH9YmivW\",\n" +
            "        \"goodsPrice\": 3799,\n" +
            "        \"originalPrice\": 3888,\n" +
            "        \"goodsName\": \"全新 华为P20 6+128G 全色\",\n" +
            "        \"subtitleList\": [],\n" +
            "        \"conditionId\": 1,\n" +
            "        \"version\": \"\",\n" +
            "        \"memory\": \"128G\",\n" +
            "        \"color\": \"黑色\",\n" +
            "        \"newLog\": 3\n" +
            "      },\n" +
            "      {\n" +
            "        \"goodsId\": \"379\",\n" +
            "        \"goodsCode\": \"1540468956725\",\n" +
            "        \"discoverImg\": \"http://qiniu.rrenji.com/FoV35NIHQH2VfeVBdnkhEuxJjjp3\",\n" +
            "        \"goodsPrice\": 2220,\n" +
            "        \"originalPrice\": 2488,\n" +
            "        \"goodsName\": \"全新 华为平板M5 10寸32G\",\n" +
            "        \"subtitleList\": [],\n" +
            "        \"conditionId\": 1,\n" +
            "        \"version\": \"国行\",\n" +
            "        \"memory\": \"32G\",\n" +
            "        \"color\": \"金色\",\n" +
            "        \"newLog\": 2\n" +
            "      }\n" +
            "    ],\n" +
            "    \"hotAndroid\": [\n" +
            "      {\n" +
            "        \"goodsId\": \"366\",\n" +
            "        \"goodsCode\": \"1540026007096\",\n" +
            "        \"discoverImg\": \"http://qiniu.rrenji.com/Fvo-obQ9iXsh9lRWO31ZNH9YmivW\",\n" +
            "        \"goodsPrice\": 3799,\n" +
            "        \"originalPrice\": 3888,\n" +
            "        \"goodsName\": \"全新 华为P20 6+128G 全色\",\n" +
            "        \"subtitleList\": [],\n" +
            "        \"conditionId\": 1,\n" +
            "        \"version\": \"\",\n" +
            "        \"memory\": \"128G\",\n" +
            "        \"color\": \"黑色\",\n" +
            "        \"newLog\": 3\n" +
            "      },\n" +
            "      {\n" +
            "        \"goodsId\": \"379\",\n" +
            "        \"goodsCode\": \"1540468956725\",\n" +
            "        \"discoverImg\": \"http://qiniu.rrenji.com/FoV35NIHQH2VfeVBdnkhEuxJjjp3\",\n" +
            "        \"goodsPrice\": 2220,\n" +
            "        \"originalPrice\": 2488,\n" +
            "        \"goodsName\": \"全新 华为平板M5 10寸32G\",\n" +
            "        \"subtitleList\": [],\n" +
            "        \"conditionId\": 1,\n" +
            "        \"version\": \"国行\",\n" +
            "        \"memory\": \"32G\",\n" +
            "        \"color\": \"金色\",\n" +
            "        \"newLog\": 2\n" +
            "      }\n" +
            "    ],\n" +
            "    \"ad\": [\n" +
            "      {\n" +
            "        \"adImg\": \"http://qiniu.rrenji.com/FgtZsNYFGVmZVx0VvxvDspAmelh8\",\n" +
            "        \"adUrl\": \"https://baike.baidu.com/item/%E6%B7%B1%E5%9C%B3%E4%BA%BA%E4%BA%BA%E6%9C%BA%E7%A7%91%E6%8A%80%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8/19700805\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"adImg\": \"http://qiniu.rrenji.com/FgrLMga8OnEbP53nrddJ72ubTvbb\",\n" +
            "        \"adUrl\": \"https://mp.weixin.qq.com/s/QriNBzX-zaTE4Cg0I7X28A\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"optimization\": [\n" +
            "      {\n" +
            "        \"goodsId\": \"366\",\n" +
            "        \"goodsCode\": \"1540026007096\",\n" +
            "        \"discoverImg\": \"http://qiniu.rrenji.com/Fvo-obQ9iXsh9lRWO31ZNH9YmivW\",\n" +
            "        \"goodsPrice\": 3799,\n" +
            "        \"originalPrice\": 3888,\n" +
            "        \"goodsName\": \"全新 华为P20 6+128G 全色\",\n" +
            "        \"subtitleList\": [],\n" +
            "        \"conditionId\": 1,\n" +
            "        \"version\": \"\",\n" +
            "        \"memory\": \"128G\",\n" +
            "        \"color\": \"黑色\",\n" +
            "        \"newLog\": 3\n" +
            "      },\n" +
            "      {\n" +
            "        \"goodsId\": \"379\",\n" +
            "        \"goodsCode\": \"1540468956725\",\n" +
            "        \"discoverImg\": \"http://qiniu.rrenji.com/FoV35NIHQH2VfeVBdnkhEuxJjjp3\",\n" +
            "        \"goodsPrice\": 2220,\n" +
            "        \"originalPrice\": 2488,\n" +
            "        \"goodsName\": \"全新 华为平板M5 10寸32G\",\n" +
            "        \"subtitleList\": [],\n" +
            "        \"conditionId\": 1,\n" +
            "        \"version\": \"国行\",\n" +
            "        \"memory\": \"32G\",\n" +
            "        \"color\": \"金色\",\n" +
            "        \"newLog\": 2\n" +
            "      }\n" +
            "    ],\n" +
            "    \"community\": [\n" +
            "      {\n" +
            "        \"communityImg\": \"http://qiniu.rrenji.com/FgtZsNYFGVmZVx0VvxvDspAmelh8\",\n" +
            "        \"CommunityContent\": \"5 年损失数十亿美元：苹果与'骗保'团伙的攻防战\",\n" +
            "        \"CommunityDesc\": \"苹果在过去的数年里，一直在应对国内专业的骗保团队。而在顶峰时期，有多达 60% 的iPhone 保修期内维修都…\",\n" +
            "        \"CommunityUrl\": \"http://qiniu.rrenji.com/FoV35NIHQH2VfeVBdnkhEuxJjjp3\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"communityImg\": \"http://qiniu.rrenji.com/FgtZsNYFGVmZVx0VvxvDspAmelh8\",\n" +
            "        \"CommunityContent\": \"5 年损失数十亿美元\",\n" +
            "        \"CommunityDesc\": \"苹果在过去的数年里，一直在应对国内专业的骗保团队。而在顶峰时期，…\",\n" +
            "        \"CommunityUrl\": \"http://qiniu.rrenji.com/FoV35NIHQH2VfeVBdnkhEuxJjjp3\"\n" +
            "      }\n" +
            "    ], \n" +
            "    \"RHome\": [\n" +
            "      { \n" +
            "        \"RHomeImg\": [\n" +
            "          \"http://qiniu.rrenji.com/FgtZsNYFGVmZVx0VvxvDspAmelh8\",\n" +
            "          \"http://qiniu.rrenji.com/FgtZsNYFGVmZVx0VvxvDspAmelh8\",\n" +
            "          \"http://qiniu.rrenji.com/FgtZsNYFGVmZVx0VvxvDspAmelh8\"\n" +
            "        ],\n" +
            "        \"RHomeContent\": \"深圳福田区人人之家旗舰店\",\n" +
            "        \"RHomeDesc\": \"这是小米上市港交所之前公开募股时，雷军发表的公开信中所阐述的小米硬件产品的愿景，或者说是小米自己努力的方向。\",\n" +
            "        \"RHomeUrl\": \"http://qiniu.rrenji.com/FoV35NIHQH2VfeVBdnkhEuxJjjp3\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"state\": \"0\"\n" +
            "  }\n" +
            "}";

    private Context mContext;

    public HomeModelImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void getCategoryDetailById(Context mContext, String categoryId, int page, int rows, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.home.getCategoryDetailById");
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        paramBuilder.add("categoryId", categoryId);
        paramBuilder.add("rows", rows);
        paramBuilder.add("page", page);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }

    @Override
    public void getNewHomeGoodsMoreByTypes(Context mContext, int type, int pageNum, int rows, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.home.getNewHomeGoodsMoreByTypes");
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
        paramBuilder.add("type", type);
        paramBuilder.add("rows", rows);
        paramBuilder.add("page", pageNum);
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
    }

    @Override
    public void getHomeList(Context mContext, int pageNum, XUtils.ResultListener resultListener) {
        String url = ServiceHelper.buildUrl("api.v2.home.getNewHomeData");
//        String sessionKey = Contetns.sessionKey;
//        long timeStamp = System.currentTimeMillis();
//        String token = "1";
//        url = url + sessionKey + "/" + timeStamp + "/" + pageNum + "/" + token;
        ServiceHelper.ParamBuilder paramBuilder = new ServiceHelper.ParamBuilder(mContext);
////        paramBuilder.add("sessionKey", "1");
////        paramBuilder.add("timeStamp", System.currentTimeMillis());
////        paramBuilder.add("pageNum", pageNum);
////        paramBuilder.add("token", "1");
        Map<String, String> params = paramBuilder.build();
        XUtils.getInstance().get(url, params, resultListener);
//        resultListener.onResponse(json);
    }
    private String readFile() {
        Resources res = mContext.getResources();
        InputStream in = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        sb.append("");
        try {
            in = res.openRawResource(R.raw.home);
            String str;
            br = new BufferedReader(new InputStreamReader(in, "GBK"));
            while ((str = br.readLine()) != null) {
                sb.append(str);
                sb.append("\n");
            }
        } catch (Resources.NotFoundException e) {
            Toast.makeText(mContext, "文本文件不存在", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            Toast.makeText(mContext, "文本编码出现异常", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(mContext, "文件读取错误", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return sb.toString();
    }


}
