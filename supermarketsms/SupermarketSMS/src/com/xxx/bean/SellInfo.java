package com.xxx.bean;

import java.util.Date;

public class SellInfo {
    private  int goodNumber; //销售的商品条码
    private  int Quantity; //销售商品数量
    private Date time; //销售商品日期日期
    private  String eNumber; //销售的员工编号
    private  String vipNumber; //会员编号
    private double totalCost; //数量乘以价格

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getVipTotalCost() {
        return vipTotalCost;
    }

    public void setVipTotalCost(double vipTotalCost) {
        this.vipTotalCost = vipTotalCost;
    }

    private double vipTotalCost; //会员总价

    public int getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(int goodNumber) {
        this.goodNumber = goodNumber;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String geteNumber() {
        return eNumber;
    }

    public void seteNumber(String eNumber) {
        this.eNumber = eNumber;
    }

    public String getVipNumber() {
        return vipNumber;
    }

    public void setVipNumber(String vipNumber) {
        this.vipNumber = vipNumber;
    }
}
