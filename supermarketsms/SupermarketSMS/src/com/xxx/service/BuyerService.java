package com.xxx.service;

import com.xxx.bean.Employee;
import com.xxx.bean.Good;
import com.xxx.dao.impl.EmployeeDaoImpl;
import com.xxx.dao.impl.GoodsDaoImpl;
import com.xxx.service.impl.ClockUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuyerService {
    private GoodsDaoImpl goodsDao = new GoodsDaoImpl();
    private Scanner in = new Scanner(System.in);
    private EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
    private Employee employee;
    private ClockUtil clockUtil =new ClockUtil();
    public BuyerService(Employee employee){
        this.employee = employee;
    }


    private void goodsOperation() {
        List<Good> list = new ArrayList<>();
        String num;
        do {
            show();
            num = in.next();
            if (num.equals("1")) {
                list = goodsDao.queryAll();
                goodsShow(list);

            } else if (num.equals("2")) {
                list = goodsDao.getGoodsInfo();
                if (list.size() > 0)
                    goodsShow(list);
                else
                    System.out.println("暂无稀缺商品...");
            } else if (num.equals("3")) {
                System.out.print("请输入要查询的商品编号:");
                int goodNum = in.nextInt();
                Good g = goodsDao.getGood(goodNum);
                if (g != null) {
                    goodsShow(g);
                } else
                    System.out.println("没有该商品编号!");
            } else if (num.equals("4")) {
                System.out.print("请输入要补充库存的商品编号:");
                int goodNum = in.nextInt();
                Good g = goodsDao.getGood(goodNum);
                if (g != null) {
                    System.out.println("[ 商品<" + g.getName() + "> ] old information");
                    goodsShow(g);
                    updateInventory(g);
                } else
                    System.out.println("没有该商品编号!");
            } else if (num.equals("0"))
                return;
            else
                System.out.println("输入错误!");
            System.out.println("-----------------按b键返回------------------");
            num = in.next();
        } while (true);
    }

    //更新商品的库存
    private void updateInventory(Good g) {
        int count = 0;
        do {
            System.out.print("[ 商品<" + g.getName() + "> ]添加inventory的数量:");
            count = in.nextInt();
            System.out.print("是否确认?Y/N:");
            if (in.next().equals("Y")) {
                if (count > 0) {
                    g.setInventory(g.getInventory() + count);
                    boolean flag = goodsDao.updateGood(g);
                    if (flag) {
                        System.out.println("[ 商品<" + g.getName() + "> ]添加库存成功!");
                        System.out.println("[ 商品<" + g.getName() + "> ] new information:");
                        goodsShow(g);
                    } else
                        System.out.println("[ 商品<" + g.getName() + "> ]添加库存失败!");
                    return;
                } else
                    System.out.println("输入数量错误!不可以为负值!请重新输入");
            }
        } while (true);

    }


    //显示商品信息
    private void goodsShow(Object obj) {
        System.out.println("---------------------------------------------------");
        System.out.println("编号\t\t商名\t\t\t单价\t\t\t会员单价\t\t库存");
        if(obj instanceof List)
            for (Good good : (List<Good>)obj) {
                System.out.println(good.toString());
            }
        else if(obj instanceof Good)
            System.out.println(((Good)obj).toString());
        System.out.println("--------------------------------------------------");

    }

    //采购员登录的菜单
    public void menu() {
        do {
            System.out.println("==================================");
            System.out.println("欢迎采购员[" + employee.getUsername() + "]");
            System.out.println("\t1.商品库存管理");
            System.out.println("\t2.上班打卡");
            System.out.println("\t3.下班打卡");
            System.out.println("\t0.返回登录界面");
            System.out.println("==================================");
            String num = in.next();
            if (num.equals("1"))
                goodsOperation();
            else if (num.equals("2"))
               clockUtil.clockIn(employee);
            else if (num.equals("3"))
                clockUtil.clockOut(employee);
            else return;
        } while (true);
    }

    //商品库存管理的菜单
    public void show() {

        System.out.println("==================================");
        System.out.println("欢迎采购员[" + employee.getUsername() + "]");
        System.out.println("\t1.查询所有商品库存");
        System.out.println("\t2.查询稀缺商品库存");
        System.out.println("\t3.查询一个商品库存");
        System.out.println("\t4.补充一种商品库存");
        System.out.println("\t0.返回采购员界面");
        System.out.println("==================================");
    }

    //业务商品库存管理
    //查询所有商品库存
    //查询稀缺商品库存
    //查询一种商品
    //补充一种商品

    //上班打卡
    //下班打卡
}
