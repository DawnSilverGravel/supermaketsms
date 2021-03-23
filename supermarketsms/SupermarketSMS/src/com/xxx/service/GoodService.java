package com.xxx.service;

import com.xxx.bean.Good;
import com.xxx.dao.impl.GoodsDaoImpl;

import java.util.List;
import java.util.Scanner;

public class GoodService {
    private GoodsDaoImpl goodsDao = new GoodsDaoImpl();
    private Scanner in = new Scanner(System.in);

    //查询所有商品信息
    public void getAllGoodInfo() {
        goodsShow(goodsDao.queryAll());
    }

    //查询某个商品的信息
    public Good getGoodInfo() {
        int goodNum = in.nextInt();
        Good g = goodsDao.getGood(goodNum);
        if (g != null) {
            goodsShow(g);
        } else
            System.out.println("没有该商品编号!");
        return g;
    }

    //更新某商品的定价
    public void updateGoodPrice() {
        Good good = getGoodInfo();
        if (good == null)
            return;

        double price = good.getPrice();
        double vipPrice = good.getVipPrice();
        while (true) {
            System.out.print("修改单价?y/n:");
            if (in.next().equals("y")) {
                System.out.print("输入新的单价:");
                good.setPrice(in.nextDouble());
            }
            System.out.print("修改会员单价?y/n:");
            if (in.next().equals("y")) {
                System.out.println("输入新的会员单价:");
                good.setVipPrice(in.nextDouble());
            }
            System.out.print("确认修改?y/n:");
            if (in.next().equals("y")) {
                if(good.getPrice()==price&&good.getVipPrice()==vipPrice){
                    System.out.println("您未做任何修改!");
                    return;
                }
                if(goodsDao.updateGood(good))
                    System.out.println("更新商品定价成功!");
                else
                    System.out.println("更新商品定价失败!");
                return;
            } else
            {
                System.out.println("退出修改?y/n:");
                if(!in.next().equals("y")){
                    good.setPrice(price);
                    good.setVipPrice(vipPrice);
                    continue;
                }
                else
                    return;
            }

        }
    }

    //上架某商品
    public void goodsShelves(){
        List<Good> list =goodsDao.queryAll();
        Good good = new Good();
        String name;
        boolean flag=true;
        while (true){
            System.out.print("请输入商品名:");
            name = in.next();
            for(int i =0; i<list.size();i++){
                if(name.equals(list.get(i).getName())){
                    flag=false;
                    break;
                }
            }
            if(!flag){
                System.out.println("该商品名已经存在!请重新输入!");
                continue;
            }
            System.out.print("请输入单价:");
            good.setPrice(in.nextDouble());
            System.out.print("请输入会员单价:");
            good.setVipPrice(in.nextDouble());
            System.out.print("请输入库存:");
            good.setInventory(in.nextInt());
            System.out.print("确认上架该商品?y/n:");
            if(in.next().equals("y")){
                if(goodsDao.addGood(good)){
                    System.out.println("上架该商品成功!");
                    goodsShow(good);
                }
                else
                    System.out.println("上架该商品失败!");
            }
            else{
                System.out.println("您取消上架该商品!");
                return;
            }
        }
    }

    //下架某商品
    public void delGood() {
        Good good = getGoodInfo();
        if (good == null)
            return;
        System.out.print("检测到上述一条商品信息,确认下架该商品?y/n:");
        if (in.next().equals("y")) {
            if (goodsDao.delGood(good.getNumber()))
                System.out.println("下架该商品成功!");
            else
                System.out.println("下架该商品失败!");
        } else
            System.out.println("您取消下架该商品操作!");
    }

    private void goodsShow(Object obj) {
        System.out.println("---------------------------------------------------");
        System.out.println("编号\t\t商名\t\t\t单价\t\t\t会员单价\t\t库存");
        if (obj instanceof List)
            for (Good good : (List<Good>) obj) {
                System.out.println(good.toString());
            }
        else if (obj instanceof Good)
            System.out.println(((Good) obj).toString());
        System.out.println("--------------------------------------------------");

    }
}
