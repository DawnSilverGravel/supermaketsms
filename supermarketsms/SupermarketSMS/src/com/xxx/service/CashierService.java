package com.xxx.service;

import com.xxx.bean.Employee;
import com.xxx.bean.Good;
import com.xxx.bean.SellInfo;
import com.xxx.bean.VIP;
import com.xxx.dao.impl.EmployeeDaoImpl;
import com.xxx.dao.impl.GoodsDaoImpl;
import com.xxx.dao.impl.SellInfoDaoImpl;
import com.xxx.dao.impl.VIPDaoImpl;
import com.xxx.service.impl.ClockUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CashierService {
    private GoodsDaoImpl goodsDao = new GoodsDaoImpl();
    private EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
    private SellInfoDaoImpl sellInfoDao =new SellInfoDaoImpl();
    private Scanner in = new Scanner(System.in);
    private Employee employee;
    private List<Good> goodList = new ArrayList<>();
    private VIPDaoImpl vipDao = new VIPDaoImpl();
    private ClockUtil clockUtil = new ClockUtil();
    public CashierService(Employee employee) {
        this.employee = employee;
    }

    public void menu() {
        String num;
        do {
            System.out.println("==================================");
            System.out.println("欢迎<收银员>[" + employee.getUsername() + "]");
            System.out.println("\t1.收银结算");
            System.out.println("\t2.会员账户管理");
            System.out.println("\t3.上班打卡");
            System.out.println("\t4.下班打卡");
            System.out.println("\t0.退出登录");
            System.out.println("==================================");
            num = in.next();
            if (num.equals("1"))
                settlement();
            else if (num.equals("2"))
                vipOperation();
            else if (num.equals("3"))
                clockUtil.clockIn(employee);
            else if (num.equals("4"))
                clockUtil.clockOut(employee);
            else if (num.equals("0"))
                return;
            else System.out.println("操作失败!");
            ;
        } while (true);
    }

    //收银结算
    public void settlement() {
        int number;
        int count;
        List<SellInfo> list = new ArrayList<>();
        do {
            System.out.print("输入商品编号:");
            number = in.nextInt();
            System.out.print("输入商品数量:");
            count = in.nextInt();
            System.out.print("是否确认y/n:");
            if (in.next().equals("y")) {
                SellInfo sellInfo = insertSellInfo(number, count);
                if (sellInfo == null) {
                    System.out.println("没有该商品编号!");
                    continue;
                } else {
                    list.add(sellInfo);
                    System.out.println("" + sellInfo.getGoodNumber() + sellInfo.getQuantity() + sellInfo.getTime() + sellInfo.geteNumber() + sellInfo.getVipNumber());
                }
            }
            System.out.println("1.继续扫描商品  2.扫描商品完成");
            if (in.next().equals("2")) {
                commit(list);
                System.out.print("---b返回上一层----");
                in.next();
                return;
            }
        } while (true);


    }

    public void vipOperation(){
        String num;
        while(true){
            System.out.println("==================================");
            System.out.println("<收银员>[" + employee.getUsername() + "] 会员账户管理");
            System.out.println("\t1.查询会员积分");
            System.out.println("\t2.添加会员");
            System.out.println("\t0.退出登录");
            System.out.println("==================================");
            num = in.next();
            if(num.equals("1")){
                VIP vip=search();
                if(vip!=null){
                    System.out.println("会员卡名\t\t\t会员名\t积分\t手机号\t\t\t注册日期");
                    System.out.println( vip.getVipNumber()+"\t"+vip.getVipName()+"\t"+vip.getVipScore()+
                            "\t"+vip.getVipPhone()+"\t"+vip.getVipDate());
                }
            }
            else if(num.equals("2")){
                addVIP();
            }
            else
                return;
            System.out.println("-------按b键返回上一层--------");
            in.next();
        }


    }
    //查找VIP用户
    private VIP search() {
        //查找VIP

        String phone;

        VIP vip = new VIP();

        System.out.println("输入手机号码:");
        phone = in.next();
        vip = vipDao.getVIPInfo(phone);
        if (vip == null) {
            System.out.println("找不到该会员");
        }
        return vip;


    }

    //添加会员
    private void addVIP(){
        String phone;
        String name;
        int num= 0;
        String str="0";
        List<VIP> list = vipDao.queryAll();
        boolean flag ;
        while(true){
            flag= true;
            System.out.print("请输入手机号:");
            phone = in.next();
            if(phone.length()<11)
            {
                System.out.println("手机号为11位");
                continue;
            }
            for(int i=0;i<list.size();i++){
                if (list.get(list.size() - 1).getVipNumber().length() > 6) {
                    str= list.get(list.size()-1).getVipNumber().substring(6);
                    if(num<Integer.parseInt(str))
                        num=Integer.parseInt(str);
                }
                if(phone.equals(list.get(i).getVipPhone())){

                    flag = false;
                    break;
                }
            }
            if(!flag){
                System.out.println("手机号重复!1.重新输入 2.退出添加会员");
                if(in.next().equals("1"))
                    continue;
                else
                    break;
            }
            else
            {
                System.out.print("请输入会员名:");
                name=in.next();
                str = "vip201"+(num+1);
                VIP newVip = new VIP();
                Date date = new Date();
                newVip.setVipNumber(str);
                newVip.setVipName(name);
                newVip.setVipPhone(phone);
                newVip.setVipDate(new java.sql.Date(date.getTime()));
                if(vipDao.addVIP(newVip))
                    System.out.println("添加会员成功!");
                else
                    System.out.println("添加会员失败!");
                System.out.print("-----------按b返回上一层----------------");
                in.next();
                break;

            }
        }

    }

    //执行结算
    private void commit(List<SellInfo> list) {
        VIP vip =null;
        do {
            System.out.print("1.普通用户  2.VIP用户:");
            if (in.next().equals("2")) {
                if((vip=search())==null)
                    continue;
                for(int i=0;i<list.size();i++){
                    list.get(i).setVipNumber(vip.getVipNumber());
                }
            } else
                break;
        } while (true);
        double cost=0;
        double vipCost=0;
        for (SellInfo sellInfo:list){
                System.out.println(sellInfo.toString());
                cost+=sellInfo.getTotalCost();
                vipCost+= sellInfo.getVipTotalCost();

        }
        System.out.println("你需要支付:￥");
       if(vip==null){
           System.out.println(cost);
       }else
       {
           System.out.println(vipCost);
           int score = (int)vipCost/10;
           vip.setVipScore(vip.getVipScore()+score);
           //更新会员积分
           vipDao.updateVIP(vip);
       }
       sellInfoDao.addSellInfo(list);

       goodsDao.updateGood(goodList);


    }

    //添加单个单价信息
    private SellInfo insertSellInfo(int number, int count) {
        Good good = goodsDao.getGood(number);
        if (good != null) {
            if(good.getInventory()-count<0){
                return null;
            }
            for(int i=0;i<goodList.size();i++)
            {
                if(good.getNumber()==goodList.get(i).getNumber()){
                    goodList.get(i).setInventory( goodList.get(i).getInventory()-count);
                    break;
                }
                if(i==goodList.size()-1){
                    good.setInventory(good.getInventory()-count);
                    goodList.add(good);
                }
            }

            Date date = new Date();
            SellInfo sellInfo = new SellInfo();
            sellInfo.setGoodNumber(good.getNumber());
            sellInfo.seteNumber(employee.getNumber());
            sellInfo.setTime(date);
            sellInfo.setQuantity(count);
            sellInfo.setVipTotalCost(good.getVipPrice() * count);
            sellInfo.setTotalCost(good.getPrice() * count);
            return sellInfo;
        }
        return null;
    }

    //收银结算
    //更新商品库存
    //添加账单信息
    //如果是会员,根据消费的金额修改会员的积分,假设每消费10元获得一积分
    //会员管理
    //查询会员积分
    //添加一个会员用户
    //

}
