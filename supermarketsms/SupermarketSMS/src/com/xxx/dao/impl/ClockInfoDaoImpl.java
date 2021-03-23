package com.xxx.dao.impl;

import com.xxx.bean.ClockInfo;
import com.xxx.bean.Employee;
import com.xxx.dao.IClockInfoDao;
import com.xxx.utils.DBUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClockInfoDaoImpl extends DBUtil<ClockInfo> implements IClockInfoDao {


    @Override
    public ClockInfo getEntity(ResultSet rs) throws SQLException {
        ClockInfo clockInfo = new ClockInfo();
        clockInfo.setClockId(rs.getInt(1));
        clockInfo.setEmployeeNo(rs.getString(2));
        clockInfo.setClockInTime(rs.getTimestamp(3));
        clockInfo.setClockOffTime(rs.getTimestamp(4));
        clockInfo.setClockDate(rs.getDate(5));
        return clockInfo;
    }

    @Override
    public String onDuty(Employee e) {
       String part="clock_in_time";
        return clock(e,part);
    }

    @Override
    public String outDuty(Employee e) {
        String part = "clock_off_time";

        return clock(e,part);
    }

    @Override
    public boolean updateAnEmployeeInfoOfDay(ClockInfo info) {
       return update("update clock_info set clock_in_time=?,clock_off_time=? where clock_date=? and employee_no=?",
                info.getClockInTime(),info.getClockOffTime(),info.getClockDate(),info.getEmployeeNo());

    }

    private String clock(Employee e,String part){
        Date date = new Date();
        java.sql.Date clockDate = new java.sql.Date(date.getTime());
        Timestamp tt= new Timestamp(date.getTime());

        List<ClockInfo> list=query("select * from clock_info where clock_date=?",clockDate);
        for(int i=0;i<list.size();i++){
            if(e.getNumber().equals(list.get(i).getEmployeeNo())){
                //如果存在当天该员工的数据就修改数据
                if(part.equals("clock_in_time")){
                    if(list.get(i).getClockInTime()!=null)
                        return "已经上班打过卡";
                }
                else
                    if(list.get(i).getClockOffTime()!=null)
                        return "已经下班打过卡";

                if(update("update clock_info set  "+part+"=? where clock_date=? and employee_no=? ",tt,clockDate,e.getNumber()))
                    return "已打卡成功,打卡时间:"+tt;
                else
                    return "已打卡失败";
            }
        }
        //不存在该数据就添加该员工打卡数据
        if(update("insert into clock_info(employee_no,"+part+", clock_date) values(?,?,?)",e.getNumber(),tt,clockDate))
            return "已打卡成功,打卡时间:"+tt;
        else
            return "已打卡失败";
    }
}
