package com.xxx.dao;

import com.xxx.bean.ClockInfo;
import com.xxx.bean.Employee;



public interface IClockInfoDao {
    //上班打卡
    public String onDuty(Employee e);
    //下班打卡
    public String outDuty(Employee e);
    //修改某个员工某年某月某天的打卡记录
    public boolean updateAnEmployeeInfoOfDay(ClockInfo info);
}
