package org.jimei.jcclub.model.vo;


/**
 * 返回类
 *
 * @author yexiaoling
 * @date 2017/4/29
 */
public class ReturnVo<T> extends BaseReturnVo {

    private T data;

    public ReturnVo() {
    }

    public ReturnVo(String code, String msg) {
        super(code, msg);
    }

    public static <T> ReturnVo<T> success(T data) {
        ReturnVo<T> returnVo = new ReturnVo<>(SUCCESS_DEFAULT_CODE, SUCCESS_DEFAULT_DESC);
        returnVo.setData(data);
        return returnVo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
