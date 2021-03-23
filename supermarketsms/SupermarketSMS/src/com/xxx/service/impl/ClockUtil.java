package com.xxx.service.impl;

import com.xxx.bean.Employee;
import com.xxx.dao.impl.ClockInfoDaoImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ClockUtil {

    private ClockInfoDaoImpl clockInfoDao = new ClockInfoDaoImpl();

    //上班打卡
    public void clockIn(Employee employee) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String nowTime = sdf.format(date);
        int ruleTime = 900000; //9点上班
        int endTime = 113000; //11点打卡上午算旷工
        if(Integer.parseInt(nowTime)>ruleTime&&Integer.parseInt(nowTime)<endTime){
            System.out.println("提示:迟到打卡");
        }
        if(Integer.parseInt(nowTime)>endTime)
            System.out.println("提示:旷工打卡");
        String info = clockInfoDao.onDuty(employee);
        System.out.println("尊敬的" + employee.getUsername() + ",您"+info+"!");
    }

    //下班打卡
    public void clockOut(Employee employee) {
        Scanner in = new Scanner(System.in);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String nowTime = sdf.format(date);
        int ruleTime = 183000; //18点30分下班
        if(Integer.parseInt(nowTime)<ruleTime){
            System.out.print("未到打卡时间?确定打卡?y/n:");
            if(!in.next().equals("y")){
                System.out.println("您取消下班打卡!");
                return;
            }
        }
        String info = clockInfoDao.outDuty(employee);
        System.out.println("尊敬的" + employee.getUsername() + ",您" + info+"!");
    }


}
