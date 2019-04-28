package org.jimei.jcclub.utils;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yexiaoling
 * @since 2019/02/25
 * @description 聚合数据发送短信工具类
 */
public class SendMsgUtil {

    private static final String APP_KEY = "cbd63bd044584ec06c028c93e42668d5";
    private static final String URL = "http://v.juhe.cn/sms/send";


    /**
     *
     * @param phone 发送的手机号
     * @param tplId 聚合数据配置的模板id
     * @return 发送结果
     */
    public static Boolean send(String phone, String tplId) {
        String result = null;
        Map<String, String> params = new HashMap<>();//请求参数
        params.put("mobile", phone);//接受短信的用户手机号码
        params.put("tpl_id", tplId);//您申请的短信模板ID，根据实际情况修改
        params.put("key", APP_KEY);//应用APPKEY(应用详细页查询)
        try {
            result = HttpUtil.request(URL, params, HttpUtil.GET);
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                System.out.println(object.get("result"));
                return true;
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
