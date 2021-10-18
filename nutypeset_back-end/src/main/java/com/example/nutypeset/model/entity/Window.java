package com.example.nutypeset.model.entity;

public class Window {
    private Integer fid;//every file has a unique file id
    private Integer wid;//every window has a in-file unique window id
    private String windowType;
    private String code;
    private String contends;
    private String otherParameters;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public String getWindowType() {
        return windowType;
    }

    public void setWindowType(String windowType) {
        this.windowType = windowType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOtherParameters() {
        return otherParameters;
    }

    public String getContends() {
        return contends;
    }

    public void setContends(String contends) {
        this.contends = contends;
    }

    public void setOtherParameters(String otherParameters) {
        this.otherParameters = otherParameters;
    }
}
