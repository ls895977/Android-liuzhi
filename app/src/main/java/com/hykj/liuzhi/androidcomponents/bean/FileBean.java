package com.hykj.liuzhi.androidcomponents.bean;

import java.io.Serializable;

public class FileBean implements Serializable {
    public FileBean(String videoName, String path, String src) {
        this.videoName = videoName;
        this.path = path;
        this.src = src;
    }

    String videoName;
    String path;
    String src;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
