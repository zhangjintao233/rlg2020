package com.itdr.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Users {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String uhone;

    private String wenti;

    private String daan;

    private String img;

    private Integer money;

    private String role;
    @JsonFormat(locale ="zh", timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(locale ="zh", timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String ip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getUhone() {
        return uhone;
    }

    public void setUhone(String uhone) {
        this.uhone = uhone == null ? null : uhone.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getWenti() {
        return wenti;
    }

    public void setWenti(String wenti) {
        this.wenti = wenti;
    }

    public String getDaan() {
        return daan;
    }

    public void setDaan(String daan) {
        this.daan = daan;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", uhone='" + uhone + '\'' +
                ", wenti='" + wenti + '\'' +
                ", daan='" + daan + '\'' +
                ", img='" + img + '\'' +
                ", money=" + money +
                ", role='" + role + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", ip='" + ip + '\'' +
                '}';
    }
}