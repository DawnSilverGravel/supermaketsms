package com.xxx.bean;

import java.sql.Date;

public class VIP {
    private String vipNumber; //会员编号
    private String vipName; //会员名字
    private int vipScore; //会员积分
    private String vipPhone; //会员电话号码
    private Date vipDate; //会员注册日期

    public String getVipNumber() {
        return vipNumber;
    }

    public void setVipNumber(String vipNumber) {
        this.vipNumber = vipNumber;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName;
    }

    public int getVipScore() {
        return vipScore;
    }

    public void setVipScore(int vipScore) {
        this.vipScore = vipScore;
    }

    public String getVipPhone() {
        return vipPhone;
    }

    public void setVipPhone(String vipPhone) {
        this.vipPhone = vipPhone;
    }

    public Date getVipDate() {
        return vipDate;
    }

    public void setVipDate(Date vipDate) {
        this.vipDate = vipDate;
    }
}
