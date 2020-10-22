package cn.stonedoors.music.dto;

import java.io.Serializable;
import java.util.List;

public class ResponseData implements Serializable {
    private static final long serialVersionUID=-1L;
    private Long total;
    private List<MusicItem> list;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<MusicItem> getList() {
        return list;
    }

    public void setList(List<MusicItem> list) {
        this.list = list;
    }
}
