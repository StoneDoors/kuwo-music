package cn.stonedoors.music.contants;

import java.io.Serializable;

public class BaseResult<T> implements Serializable {
    private static final long serialVersionUID = 7277594240016924327L;
    private int code = 200;
    private String msg= "请求成功";
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
