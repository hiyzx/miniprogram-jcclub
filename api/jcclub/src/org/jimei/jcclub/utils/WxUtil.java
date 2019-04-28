package org.jimei.jcclub.utils;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yezhaoxing
 * @since 2019/03/14
 */
public class WxUtil {

    private static final String APPID = "wx3f17e7fbd19b1272";
    private static final String SECRET = "c56037fce7357558ee4ad49f451860d6";

    private static final String URL = "https://api.weixin.qq.com/sns/jscode2session";

    public static String getOpenid(String code) {
        Map<String, String> params = new HashMap<>(4);
        params.put("appid", APPID);
        params.put("secret", SECRET);
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        String response = HttpUtil.request(URL, params, HttpUtil.GET);
        return JSON.parseObject(response).getString("openid");
    }
}
