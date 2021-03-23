package com.xxx.bean;

import java.sql.Timestamp;

public class Turnover extends SellInfo {
//   private int sCNumber; //销售商品编号
//   private int sQuantity;  //销售商品数量
//   private Timestamp sTime;
//   private String sENumber;
//   private String sVipNumber;

   private double price;
   private double vipPrice;

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public double getVipPrice() {
      return vipPrice;
   }

   public void setVipPrice(double vipPrice) {
      this.vipPrice = vipPrice;
   }
}
