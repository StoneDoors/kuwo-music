package cn.stonedoors.music.dto;

import java.io.Serializable;

public class ResponseDto implements Serializable {
    private static final long serialVersionUID=-1L;
    private int code;
    private Long curTime;
    private ResponseData data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Long getCurTime() {
        return curTime;
    }

    public void setCurTime(Long curTime) {
        this.curTime = curTime;
    }

    public ResponseData getData() {
        return data;
    }

    public void setData(ResponseData data) {
        this.data = data;
    }
}
