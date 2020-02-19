package com.itdr.pojo;

public class Gwc{
    private Integer id;

    private Integer uid;

    private Integer pid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPmoney() {
        return pmoney;
    }

    public void setPmoney(String pmoney) {
        this.pmoney = pmoney;
    }

    private String pname;

    private String pmoney;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }
}