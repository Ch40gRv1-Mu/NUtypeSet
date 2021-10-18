package com.example.nutypeset.model.entity;

import java.util.Date;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Date createTime;
    private String pdfsrc;
    private String latexFinalCode;

    public String getPdfsrc() {
        return pdfsrc;
    }

    public void setPdfsrc(String pdfsrc) {
        this.pdfsrc = pdfsrc;
    }

    public String getLatexFinalCode() {
        return latexFinalCode;
    }

    public void setLatexFinalCode(String latexFinalCode) {
        this.latexFinalCode = latexFinalCode;
    }

    public Integer getId() {
        return id;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return "User{"+
                "id="+id+
                ", username=" + username+'\''+
                ", password=" + password+'\''+
                '}';

    }
}
