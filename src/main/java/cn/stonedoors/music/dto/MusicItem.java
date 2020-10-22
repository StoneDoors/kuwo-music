package cn.stonedoors.music.dto;

import java.io.Serializable;

public class MusicItem implements Serializable {
    private static final long serialVersionUID=-1L;
    private Long id;
    private String pic120; //缩略图
    private String name; //歌曲名称
    private String artist; //歌手名称
    private String album; //专辑名称
    private String songTimeMinutes; //时长
    private String  rid; //下载地址代码
    private String  downUrl; //下载地址
    private String playFlag;//

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPic120() {
        return pic120;
    }

    public void setPic120(String pic120) {
        this.pic120 = pic120;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getSongTimeMinutes() {
        return songTimeMinutes;
    }

    public void setSongTimeMinutes(String songTimeMinutes) {
        this.songTimeMinutes = songTimeMinutes;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getPlayFlag() {
        return playFlag;
    }

    public void setPlayFlag(String playFlag) {
        this.playFlag = playFlag;
    }
}
