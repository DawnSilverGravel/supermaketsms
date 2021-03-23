package com.xxx.bean;

public class Good {
    private int number; //商品编号
    private String  name; //商品名字
    private double price; //价格
    private double vipPrice; //会员价格
    private int inventory; //库

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        String priceLen=""+price;
        String vipPriceLen=""+vipPrice;
        if(name.length()<3)
            name+="\t";
        if(price<10.0)
            priceLen =""+price+"\t";
        if(vipPrice<10.0)
            vipPriceLen =""+vipPrice+"\t";
        return number + "\t\t" +
                name + "\t\t" +
                priceLen + "\t\t" +
                vipPriceLen+ "\t\t" +
                inventory;
    }
}
