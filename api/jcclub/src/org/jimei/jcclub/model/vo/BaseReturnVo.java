package org.jimei.jcclub.model.vo;

/**
 * 基础的返回类
 *
 * @author yexiaoling
 * @date 2017/4/29
 */
public class BaseReturnVo {

    final static String SUCCESS_DEFAULT_DESC = "success";
    final static String SUCCESS_DEFAULT_CODE = "200";
    private String resCode;
    private String resDes;

    public BaseReturnVo() {
    }

    public BaseReturnVo(String code, String msg) {
        this.resCode = code;
        this.resDes = msg;
    }

    public static BaseReturnVo success() {
        return new BaseReturnVo(SUCCESS_DEFAULT_CODE, SUCCESS_DEFAULT_DESC);
    }

    public static BaseReturnVo fail() {
        return new BaseReturnVo("500", "异常");
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResDes() {
        return resDes;
    }

    public void setResDes(String resDes) {
        this.resDes = resDes;
    }
}
