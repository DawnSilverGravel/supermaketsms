package com.xxx.service;

import com.xxx.bean.Turnover;
import com.xxx.dao.impl.SellInfoDaoImpl;
import com.xxx.dao.impl.TurnoverDaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TurnoverService {
    private Scanner in = new Scanner(System.in);
//    private SellInfoDaoImpl sellInfoDao = new SellInfoDaoImpl();
    private TurnoverDaoImpl turnoverDao = new TurnoverDaoImpl();
    //查询总营业额
    //查询当天营业额
    public void show(double turnover,String str) {
        if(turnover==0){
            System.out.println("没有查询到"+str+"相应的营业额!");
        }
        else
            System.out.println("查询到"+str+"的营业额为"+turnover+"元");
    }

    //查询某年某月某天的营业额,通过传值来确定参数
    public void turnoverOfYMD(int num) {
        List<Integer> list =operation(num);
        if(list.size()==1)
            show(turnoverDao.getTurnoverOfYMD(list.get(0)),""+list.get(0)+"年");
        else if (list.size()==2)
            show(turnoverDao.getTurnoverOfYMD(list.get(0),list.get(1)),""+list.get(0)+"年"+list.get(1)+"月");
        else
            show(turnoverDao.getTurnoverOfYMD(list.get(0),list.get(1),list.get(2)),""+list.get(0)+"年"+list.get(1)+"月"+list.get(2)+"日");
    }

   //查询某季度的营业额
    public void turnoverOfQuarter() {
        int year;
        int quarter;
        System.out.print("请输入年:");
        year = in.nextInt();
        System.out.print("请输入季度:");
        quarter = in.nextInt();
        show(turnoverDao.getTurnoverOfQuarter(year,quarter),""+year+"年"+"第"+quarter+"季度");
    }

    //查询某年到某年的营业额
    public void turnoverYearToYear() {
        int year1;
        int year2;
        System.out.print("请输入起始年:");
        year1 = in.nextInt();
        System.out.print("请输入最终年:");
        year2 = in.nextInt();
        show(turnoverDao.getTurnoverYearToYear(year1,year2),""+year1+"年"+"---"+year2+"年");
    }

    private List<Integer> operation(int num) {
        int year;
        int month;
        int day;
        List list = new ArrayList();
        if (num > 0){
            System.out.print("请输入年:");
            year = in.nextInt();
            list.add(year);
        }

        if (num > 1){
            System.out.print("请输入月:");
            month = in.nextInt();
            list.add(month);
        }

        if (num > 2){
            System.out.print("请输入日:");
            day = in.nextInt();
            list.add(day);
        }
        return list;
    }
    //查询某季度营业额
    //查询某年营业额
    //查询某年到某年的营业额
}
