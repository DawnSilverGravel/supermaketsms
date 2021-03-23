package com.xxx.service;

import com.xxx.bean.AttendanceInfo;
import com.xxx.bean.ClockInfo;

import com.xxx.dao.impl.AttendanceInfoDaoImpl;
import com.xxx.dao.impl.ClockInfoDaoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AttendanceService {
    private AttendanceInfoDaoImpl dao = new AttendanceInfoDaoImpl();
    private ClockInfoDaoImpl clockInfoDao = new ClockInfoDaoImpl();
    private Scanner in = new Scanner(System.in);
    //查询所有的员工记录
    public void queryAll(){
        show(dao.getAllAttendanceInfo());
    }
    public void queryAnEmployeeAll(String employeeNo){
        show((dao.getAnEmployeeAllAttendanceInfo(employeeNo)));
    }

    //根据条件查询员工的出勤信息
    public void queryAttendanceOfYMD(String employeeNo,int num){
        List<Integer> list = operation(num);
        if(list.size()==1)
            show(dao.getAttendanceOfEYMD(employeeNo,list.get(0)));
        if(list.size()==2)
            show(dao.getAttendanceOfEYMD(employeeNo,list.get(0),list.get(1)));
        if(list.size()==3)
            show(dao.getAttendanceOfEYMD(employeeNo,list.get(0),list.get(1),list.get(2)));
    }

    //修改某个员工某年某月某天的出勤记录
    public void updateAnEmployeeOfDay(String  employeeNo){
        List<Integer> list = operation(3);
        List<AttendanceInfo> list1 =dao.getAttendanceOfEYMD(employeeNo,list.get(0),list.get(1),list.get(2));

        if(list1.size()>0){
            AttendanceInfo ai = list1.get(0);
            if(ai.getClockOffTime()!=null&&ai.getClockInTime()!=null){
                System.out.println("该天此员工没有却卡记录,无需修改!");
                return;
            }
            System.out.print("是否修改信息?y/n:");
            if(!in.next().equals("y"))
                return;
            ClockInfo info = new ClockInfo();
            info.setEmployeeNo(employeeNo);
            info.setClockDate(ai.getWorkDate());
            info.setClockInTime(ai.getClockInTime());
            info.setClockOffTime(ai.getClockOffTime());
            while(true){
                if(ai.getClockInTime()==null){
                    System.out.print("将上班打卡设为正常打卡?/n:");
                    if(in.next().equals("y")){
                        Timestamp timestamp =Timestamp.valueOf(""+ai.getWorkDate()+" 09:00:00");
                        info.setClockInTime(timestamp);
                    }
                }
                if(ai.getClockOffTime()==null){
                    System.out.print("将下班打卡设为正常打卡?/n:");
                    if(in.next().equals("y")){
                        Timestamp timestamp =Timestamp.valueOf(""+ai.getWorkDate()+" 18:30:00");
                        info.setClockInTime(timestamp);
                    }
                }
                System.out.print("是否确认修改?y/n:");
                //不匹配的话就跳到循环开头
                if(!in.next().equals("y")){
                    info.setClockInTime(ai.getClockInTime());
                    info.setClockOffTime(ai.getClockOffTime());
                    continue;
                }
                if(info.getClockInTime()==ai.getClockInTime()&&info.getClockOffTime()==ai.getClockOffTime())
                {
                    System.out.println("你未做任何修改!");
                    return;
                }
                else{
                    if(clockInfoDao.updateAnEmployeeInfoOfDay(info)){
                        System.out.println("修改打卡信息成功! new information:");
                        if(ai.getClockInTime()!=info.getClockInTime()){
                            ai.setDiffInStatus("正常");
                            ai.setClockInTime(info.getClockInTime());
                        }
                        if(ai.getClockOffTime()!=info.getClockOffTime()){
                            ai.setDiffOffTime("正常");
                            ai.setClockOffTime(info.getClockOffTime());
                        }
                        List aiList = new ArrayList<AttendanceInfo>();
                        aiList.add(ai);
                        show(aiList);
                    }
                    else
                        System.out.println("修改打卡信息失败!");
                }
            }
        }
    }

    private void show(List<AttendanceInfo> list){
        if(list.size()<=0)
        {
            System.out.println("没有符合条件的打卡记录!");
            return;
        }
        System.out.println("  工作日期  \t\t员工编号\t\t\t\t上班打卡\t\t\t\t\t\t下班打卡\t\t\t\t  上班状态\t\t下班状态");
        for(AttendanceInfo ai:list){
            System.out.println(ai.getWorkDate()+"\t\t"+ai.getEmployeeNo()+"\t\t"+ai.getClockInTime()+"\t\t"+ai.getClockOffTime()+"\t\t\t"+
                    ai.getDiffInStatus()+"\t\t\t  "+ai.getDiffOffTime());
        }
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
//    //查询某个员工的所有记录
//    public void queryAnEmployeeOfAll(String  employeeNo){
//        show(dao.getAnEmployeeAllAttendanceInfo(employeeNo));
//    }
//
//    //查询某个员工某年的记录
//    public void queryAnEmployeeOfYear(String  employeeNo){
//        int year;
//        System.out.print("请输入年:");
//        year=in.nextInt();
//        show(dao.getAnEmployeeAttendanceInfoOfYear(employeeNo,year));
//    }
//
//    //查询某个员工某年某月的记录
//    public void queryAnEmployeeOfMonth(String  employeeNo){
//        int year;
//        int month;
//        System.out.print("请输入年:");
//        year=in.nextInt();
//        System.out.print("请输入月:");
//        month=in.nextInt();
//        show(dao.getAnEmployeeAttendanceInfoOfMonth(employeeNo,year,month));
//    }
//
//    //查询某个员工某年某月某天出勤记录
//    public AttendanceInfo queryAnEmployeeOfDay(String  employeeNo){
//        int year;
//        int month;
//        int day;
//        System.out.print("请输入年:");
//        year=in.nextInt();
//        System.out.print("请输入月:");
//        month=in.nextInt();
//        System.out.print("请输入日:");
//        day = in.nextInt();
//        AttendanceInfo ai = dao.getAnEmployeeAttendanceInfoOfDay(employeeNo,year,month,day);
//        if(ai!=null){
//            List list = new ArrayList<AttendanceInfo>();
//            list.add(ai);
//            show(list);
//            return ai;
//        }
//        else
//            System.out.println("没有该员工当天的打卡记录");
//        return null;
//    }
//

    //
}
